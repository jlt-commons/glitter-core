# Contributing

## Build and test

```bash
jolt -M:test
```

That's the whole gate. There's no separate lint/format task configured
in this repository yet — if you add one, update this page.

## Provenance rules for ported code

Most of `src/` is a mechanical port of upstream
[Replicant](https://github.com/cjohansen/replicant), and one file
(`glitter.nexus.action_log`) ports a concept from upstream
[nexus](https://github.com/cjohansen/nexus). `NOTICE` is the
authoritative, file-by-file record of what came from where, including
every deliberate deviation from the upstream source.

Before changing a ported file:

1. Read its entry in `NOTICE` to know whether it's a byte-for-byte port,
   a mechanical rename, or carries documented deviations.
2. If your change touches upstream behavior (not just a Jolt-specific
   adaptation), consider whether the fix belongs upstream instead —
   this package exists to carry a faithful port forward, not to
   diverge from it without a documented reason.
3. Update `NOTICE` if your change adds a new deviation from upstream,
   so the next person reading it has an accurate record.

## Namespace names are a public contract

Every namespace in this package (`glitter.core`, `glitter.protocols`,
`glitter.alias`, etc.) keeps the exact name it had inside `glitter`
before the extraction. This was deliberate: existing consumers of
`glitter` didn't have to change a single `:require` line when this
package was extracted. Don't rename a namespace without a strong reason
— it's a breaking change for every downstream consumer
(`glitter`, `glitter-uikit`, and anything depending on those).

## Reporting issues

Open an issue on [this repository](https://github.com/jlt-commons/glitter-core/issues).
If the issue is actually about upstream Replicant or nexus behavior
rather than something specific to this port, consider filing it
upstream instead — see `NOTICE` for the exact upstream commits this
package was ported from.
