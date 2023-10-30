# ogimagekun
OG:Image generator

## build HTML renderer

```sh
% sbt pack
% docker build -t ogimagekun .
```

Then deploy image to Cloud Run (or other service).

## build WebP renderer worker

Configure `wrangler.toml` and `src/worker.js` for your environment.

To acquire kv namespace and get id:

```sh
% cd worker
% npx wrangler kv:namespace create OGI_WORKER
```

To deploy:

```sh
% npx wrangler deploy
```
