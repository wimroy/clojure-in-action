(ns clojure-in-action.core
  (:require
    [clojure-in-action.chapter01 :as ch01])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (println (ch01/runChapter01)))

