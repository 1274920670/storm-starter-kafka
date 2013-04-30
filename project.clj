(defproject storm-starter "0.0.1-SNAPSHOT"
  :source-paths ["src/clj"]
  :java-source-paths ["src/jvm"]
  :resource-paths ["multilang"]
  :aot :all
  :repositories {
                 "scala-tools" "http://scala-tools.org/repo-releases"
                  "conjars" "http://conjars.org/repo/"
                 }

  :dependencies [
;;                 [org.twitter4j/twitter4j-core "2.2.6-SNAPSHOT"]
;;                 [org.twitter4j/twitter4j-stream "2.2.6-SNAPSHOT"]
                   [commons-collections/commons-collections "3.2.1"]
                   [org.scala-lang/scala-library "2.9.2"]
                   [com.twitter/kafka_2.9.2 "0.7.0"
                    :exclusions [org.apache.zookeeper/zookeeper
                               log4j/log4j]]
                 ]

  :profiles {:dev
              {:dependencies [[storm "0.9.0-wip15"]
                              [org.clojure/clojure "1.4.0"]]}}
  :min-lein-version "2.0.0"
  )
