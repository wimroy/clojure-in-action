(ns clojure-in-action.chapter02)


(println "---------------------- Chapter-02 start")

(+ 1 2) "A String" Math/PI (Math/abs -100)

;; Only works at REPL?
;(doc +)
;(find-doc "lazy")
;(apropos 'doc)

(def x 1)
(println (cond
           (> x 0) "Greater"
           (= x 0) "Equal"
           (< x 0) "Lesser "))

;; About Whitespace
(+ 1 2 3 4 5)
(+ 1, 2, 3, 4, 5)
(+ 1,,,,, 2, 3, 4, 5)

(def a-map {:a 1 :b 2 :c 3})
(def another-map {:a 1, :b 2, :c 3})

;;;; Comments
;; One or more ;
;; or the 'comment' macro
(comment (def x 2) "this line is commented")


;;;; Clojure Data Structures

;; Characters and Strings
(println (.contains "clojure-in-action" "-"))
(println (.endsWith "file.clj" "clj"))

;; Numbers - TODO later

;; Symbols and Keywords
(println (keyword "foo"))
(println (symbol "foo" "bar"))
(println (namespace :foo/bar))
(println (namespace :foo))
(println (name "baz"))

;; Lists
(def a-list (list 1 2 3 4 5))
(println (list? a-list))
(def list+few-more-elements (conj a-list 10 11 12))
(println list+few-more-elements)

(println (peek a-list))
(println (pop a-list))
(println (count a-list))
;(def numbers (1 2 3)) ; can't do this
(def numbers '(1 2 3))                                      ; but this does


;; Vectors
(println (vector 1 2 3 4 5))
(println [1 2 3 4 5])
(def the-vector [1 2 3 4 5])
(println (get the-vector 2))
(println (get the-vector 20))                               ; returns nil

(println (nth the-vector 2))
;(println (nth the-vector 20)) ; throws an exception

(def assoc-vector (assoc the-vector 2 25))
(println assoc-vector)
(assoc the-vector 5 70)                                     ; add to the end
;(assoc the-vector 10 70) ; throws an exception adding past the end
(println (conj the-vector 100))
(println (peek the-vector))
(println (pop the-vector))
;; Vector special case used as a function to lookup the value by index
(println (the-vector 3))

;; Maps
(def a-map {:a 1, :b 2, :c 3})
(def another-map (hash-map :a 1 :b 2 :c 3))
(println (a-map :b))                                        ; Like a vector, a map is also a function that accepts a key as its arg
; Clojure keywords are also functions that look themselves up in a collection
(println (:c a-map))

; return default values if key not found
(println (a-map :z 100))
(println (:z a-map 100))

(def updated-map (assoc a-map :d 4))
(println updated-map)
(println (dissoc updated-map :a))


; Updating deeply-nested maps
(def users {:kyle {
                   :date-joined "2009-01-01"
                   :summary     {
                                 :average {
                                           :monthly 1000
                                           :yearly  12000}}}})

(assoc-in users [:kyle :summary :average :monthly] 3000)
(println users)
(println (get-in users [:kyle :summary :average :monthly]))

(update-in users [:kyle :summary :average :monthly] + 3000)


;; Sequences
(println (first (list 1 2 3)))
(println (rest (list 1 2 3)))
(println (first (vector 1 2 3)))
(println (rest (vector 1 2 3)))
(println (first {:a 1 :b 2 :c 3}))
(println (rest {:a 1 :b 2 :c 3}))
(println (first []))
(println (rest []))
(println (cons 4 [1 2 3]))                                  ; cons => construct


;;;; Program Structure

;; Functions
(defn addition-func [x y]
  (+ x y))

(def addition-function
  (fn [x y]
    (+ x y)))

;; The 'let' form
(defn average-pets []
  (/ (apply + (map :number-pets (vals users))) (count users)))

;(def users {})
;(defn average-pets []
;  (let [user-data (vals users)
;        pet-counts (map :number-pets user-data)
;        total (apply + pet-counts)]
;    (/ total (count users))))
;(average-pets)

(println (let [x 1 y 2 z (+ x y)] z))

;; Side effects with 'do'
;(if (is-something-true?)
;  (do
;    (log-message "in true branch")
;    (store-something-in-db)
;    (return-useful-value)))

;; Reader macros - TODO later


;;;; Program Flow

;; Conditionals
(println (if (> 5 2)
           "yes"
           "no"))

(println (if-not (> 5 2) "yes" "no"))

(def x 1)
(println (cond
           (> x 0) "greater"
           (< x 0) "lesser"
           (= x 0) "zero"))

(when (> 5 2)
  (println "five")
  (println "is greater")
  (println "than two")
  "done")

(when-not (< 5 2)
  (println "five")
  (println "is not greater")
  (println "than two")
  "done")

;; Logical Functions
(and)
(and :a :b :c)
(and :a nil :c)
(and :a false :c)
(and 0 "")
(or)
(or :a :b :c)
(or :a nil :c)
(or :a false :c)
(or nil false)
(or false nil)
(or 0 "")
(not true)
(not 1)
(not nil)

(= 1 1N 1/1)
(= 0.5 1/2)

; to compare numbers only
(== 1 1N 1/1)
(== 0.5 1/2)

;; Functional Iteration
; while

; loop/recur
(defn fact-loop [n]
  (loop [current n fact 1]
    (if (= current 1)
      fact
      (recur (dec current) (* fact current)))))

; doseq and dotimes
(defn run-report [user]
  (println "Running report for" user))
(defn dispatch-reporting-jobs [all-users]
  (doseq [user all-users]
    (run-report user)))
(println (dispatch-reporting-jobs ["user1" "user2"]))
(dotimes [x 5]
  (println "Value is " x))

; map
(println (map inc [0 1 2 3 4]))
(println (map + [1 2 3] [4 5 6]))
(println (map + [1 2 3] [4 5]))

; filter and remove
(defn non-zero-expenses [expenses]
  (let [non-zero? (fn [e] (not (zero? e)))]
    (filter non-zero? expenses)))
(println (non-zero-expenses [-2 -1 0 1 2]))

(defn non-zero-expenses2 [expenses]
  (remove zero? expenses))
(println (non-zero-expenses2 [-2 -1 0 1 2]))

; reduce
(defn factorial [n]
  (let [numbers (range 1 (+ n 1))]
    (reduce * numbers)))                                    ; use reductions to see intermediate steps
(factorial 5)

; for
(def chessboard-labels
  (for [alpha "abcdefgh" num (range 1 9)]
    (str alpha num)))
(println chessboard-labels)

(println (for [x (range 1 10) :when (< x 8)] x))

(defn prime? [x]
  (let [divisors (range 2 (inc (int (Math/sqrt x))))
        remainders (map (fn [d] (rem x d)) divisors)]
    (not (some zero? remainders))))

; TODO - How does this work???
(defn pairs-for-primes [n] (let [z (range 2 (inc n))]
                             (for [x z y z :when (prime? (+ x y))]
                               (list x y))))
(println (pairs-for-primes 5))


;; Threading Macros
; ->
; ->>
(defn final-amount-> [principle rate time-periods]
  (-> rate
      (/ 100)
      (+ 1)
      (Math/pow time-periods)
      (* principle)))

(defn factorial->> [n]
  (->> n
       (+ 1)
       (range 1)
       (reduce *)))
(println (factorial->> 15))

; other macros some-> some->> as-> cond-> cond->>
(as-> {"a" [1 2 3 4]} <>
      (<> "a")
      (conj <> 10)
      (map inc <>))

; TODO - understand later
(let [x 1 y 2]
  (as-> [] <>
        (if (odd? x)          (conj <> "x is odd")            <>)
        (if (zero? (rem y 3)) (conj <> "y is divisible by 3") <>)
        (if (even? y)         (conj <> "y is even")           <>)))



(println "---------------------- Chapter-02 end")
