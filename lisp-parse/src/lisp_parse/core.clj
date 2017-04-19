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

(defn clean_input
  [input]
  (into []
        (str/replace input #"\s" "")))

(defn next_expr
  "fetch next list, returning [next-expr rest-of-unparsed-string]"
  ([preparsed] (next_expr preparsed [] ["(" ")"]))
  ([preparsed cur_col [start_symbol end_symbol]]
   (let [next_symbol (str (first preparsed))
         rest (drop 1 preparsed)]
     (cond
       ;; we have a new list
           (= next_symbol start_symbol) (next_expr rest cur_col [start_symbol end_symbol])
                                        ;end of the list has been reached!
           (= next_symbol end_symbol) [cur_col rest]
                                        ;we're in the middle of a list
       :else (next_expr rest (conj cur_col next_symbol) [start_symbol end_symbol])))))

(first (next_expr (clean_input "(* (+ 1 2) 3)")))


;; (clean_input "(* (+ 1 2) 3)")


