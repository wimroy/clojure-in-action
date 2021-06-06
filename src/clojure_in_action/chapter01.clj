(ns clojure-in-action.chapter01)

;; To load this file at the REPL
;; (require 'clojure-in-action.chapter01)
;; (in-ns 'clojure-in-action.chapter01)
;; and now you can access the definitions
;;;; sayHello
;;;; => #object[clojure_in_action.chapter01$sayHello 0xc83a358 "clojure_in_action.chapter01$sayHello@c83a358"]
;;;; (sayHello)
;;;; => "Chapter-01 says Hello!"


(println "---------------------- Chapter-01 start")
(def x 5)
(defn sayHello
  []
  "Chapter-01 says Hello!")

(println (. Math PI))
(println (. Math abs -3))
(println (. "foo" toUpperCase))

(println Math/PI)
(println (Math/abs -3))
(println (.toUpperCase "foo"))

(def value (Integer. "42"))
(def anotherValue (new Integer "42"))

(defn runChapter01
  []
  (sayHello))


(println "---------------------- Chapter-01 end")