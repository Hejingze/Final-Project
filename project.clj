(defproject adventure "0.1.0-SNAPSHOT"
  :description "A parser that remembers simple facts."
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/core.match "0.3.0-alpha4"]
                 [org.clojure/data.json "0.2.6"]
                 [io.aviso/pretty "0.1.37"]]
  :main ^:skip-aot adventure.core
  :profiles {:dev {:dependencies [[midje "1.9.9"]]}}
  :plugins [[lein-marginalia "0.9.1"]
            [io.aviso/pretty "0.1.37"]
            [lein-midje "3.2.1"]])
