(ns lisp-parse.core
  (:require [clojure.string :as str]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn parse
  "parses list strings"
  [x]
  (first (next_expr
          (clean_input x))))

(defn space_evenly
  [s]
  (str/replace s #"\s*[\(\)]\s*" #(str " " %1 " ")))

(defn clean_input
  [s]
  (filter #(not (empty? %1))
          (str/split (space_evenly s) #"\s+")))

(defn next_expr
  "fetch next list, returning [next-expr rest-of-unparsed-string]"
  ([preparsed] (next_expr preparsed ["(" ")"]))
  ([preparsed [start_symbol end_symbol]]
   (let [next_symbol (str (first preparsed))
         rest (drop 1 preparsed)]
     (if (= next_symbol start_symbol)
       (next_expr rest [start_symbol end_symbol])
       (next_expr rest [next_symbol] [start_symbol end_symbol]))))
  ([preparsed cur_col [start_symbol end_symbol]]
   (let [next_symbol (str (first preparsed))
         rest (drop 1 preparsed)]
     (cond
       ;; we have a new list
       (= next_symbol start_symbol) (let [[nestled_expr new_rest] (next_expr rest)]
                                      (next_expr new_rest (conj cur_col nestled_expr) [start_symbol end_symbol]))
       ;end of the list has been reached!
       (= next_symbol end_symbol) [cur_col rest]
       ;we're in the middle of a list
       :else (next_expr rest (conj cur_col next_symbol) [start_symbol end_symbol])))))

;; (first (next_expr (clean_input "(* (+ 1 2) 3)")))

;; (parse "(* 2 (+ 1 2 10))")

;; (str/replace "(* 2 (+ 1 2))" #"\s*[\(\)]\s*" #(str " " %1 " "))
