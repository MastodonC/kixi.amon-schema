(defproject kixi.amon-schema "0.1.0-SNAPSHOT"
  :description "A Prismatic Schema for AMON v4 requests and responses."
  :url "http://github.com/MastodonC/kixi.amon-schema"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[prismatic/schema "0.2.1"]
                 [schema-contrib "0.1.3"]]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.5.1"]]}})
