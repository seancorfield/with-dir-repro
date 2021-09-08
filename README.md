# Repro

Clone the repo and then run:

```bash
clojure -T:build prep
clojure -T:build uber
```

The script prints the `:paths` and `:classpath` roots from the basis:
`src` is present in both, as expected from the `projects/example/deps.edn` file.

The `t/create-basis` call (wrapped in `with-dir`) produces the expected basis
(as seen in the printed output).

But neither the `prep` task not the `uber` task respect the `with-dir`
context so the top-level `src` folder is used instead of the `projects/example/src` folder.
