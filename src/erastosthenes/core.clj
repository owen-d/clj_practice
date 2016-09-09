(ns erastosthenes.core)

(defn buildLimiter
	"Builds a fn which limits modulo=0s through"
	[x]
	#(mod % x))

(defn hof
	"testing a high order fn"
	[a]
	#(str a %))
; 
; 
; 
; build vector/generator
; reference a constructed array
; reduce through said array. if reaches end, push to results & push a new limiter fn to results arr
; 
; 
; 
; 
; 
; 
; 
; 
; 
; 
; 
; 
; 
; 
; 
(defn -main
  "I don't do a whole lot."
  []
  (let [a (buildLimiter 2)] 
  	(println (str (a 5)))))

; (defn hof [a] (fn [b] (= a b)))
; (defn hof [a] #(= a %1))