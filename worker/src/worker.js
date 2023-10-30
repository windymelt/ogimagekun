import puppeteer from "@cloudflare/puppeteer";

export default {
	async fetch(request, env) {
		const { searchParams } = new URL(request.url);
		let title = searchParams.get("title");
		let img;
		if (title) {
		    let url = new URL(`https://ogimagekun-wivvzffbra-an.a.run.app/${title}`).toString(); // normalize
			img = await env.OGI_WORKER.get(url, { type: "arrayBuffer" });
			if (img === null) {
				const browser = await puppeteer.launch(env.MYBROWSER);
				const page = await browser.newPage();
				await page.goto(url);
			    img = await page.screenshot({ type: 'webp', clip: { height: 630, width: 1200, x: 0, y: 0 } });
				await env.OGI_WORKER.put(url, img, {
					expirationTtl: 60 * 60 * 24,
				});
				await browser.close();
			}
			return new Response(img, {
				headers: {
					"content-type": "image/webp",
				},
			});
		} else {
			return new Response(
				"Please add an ?title=title parameter"
			);
		}
	},
};
