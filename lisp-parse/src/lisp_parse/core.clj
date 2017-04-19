(ns lisp-parse.core
  (:require [clojure.string :as str]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn parse
  "parses list strings"
  [x]
  ())

(defn tbd
  ""
  [preparsed cur_expr] (tbd preparsed [])
  (case (count preparsed)
    ; empty str
    0 cur_expr
    ; parse!
    1 (conj cur_expr preparsed)
    ; multiples: shell out
    ))

(defn clean_input
  [input]
  (into []
        (str/replace input #"\s" "")))

(defn next_expr
  "fetch next list, returning [next-expr rest-of-unparsed-string]"
  ([preparsed] (next_expr [] preparsed [\( \)]))
  ;; ([preparsed [start_symbol end_symbol]]
  ;;  (let [next_symb (first preparsed)
  ;;        rest (drop 1 preparsed)]
  ;;    (if (= next_symb start_symbol)
  ;;      ;; we have a new list
  ;;      (next_expr rest [] [start_symbol end_symbol])
  ;;      ;; next expr isnt a new list
  ;;      [next_symb rest]
  ;;      )))
  ([preparsed cur_col [start_symbol end_symbol]]
   (let [next_symb (first preparsed)
         rest (drop 1 preparsed)]
     (if (= next_symb end_symbol)
       ;end of the list has been reached!
       [cur_col rest]
       ;([(conj cur_col next_symb) rest])
       (next_expr rest (conj cur_col next_symb) [start_symbol end_symbol])))))

;; (defn des_tst
;;   [x [b c]]
;;   [x b c])

;; (des_tst "a" ["b" "c"])

(first (next_expr (clean_input "(* (+ 1 2) 3)")))
