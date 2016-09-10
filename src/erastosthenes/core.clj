(ns erastosthenes.core)

(defn modulimit
	"Builds a gating modulo fn"
	[x]
	#(mod % x))

; (defn doEet
; 	[arr reducer]
; 	(doseq arr reducer))

(defn gauntlet
	[accum, gate]
	(if accum (gate accum) accum))


(defn idkyet
	"still figuring this shit out"
	[x]
	(let [inputs (range 2 (+ x 1))
		gates []
		res []]
		(doseq [value inputs]
			(if 
				(reduce 
					gauntlet
					value
					gates)
				(do (conj gates (modulimit value))
					(conj res value)
					(println (str "hit" value gates)))))))
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
  (idkyet 5))

; (defn hof [a] (fn [b] (= a b)))
; (defn hof [a] #(= a %1))