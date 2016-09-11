(ns erastosthenes.core)

(defn moduLimit
	"Builds a gating modulo fn"
	[x]
	(fn
		([a] (if (or (= a nil) (= (mod a x) 0))
			; is a multiple or was a multiple of a previous gate, disregard.
			nil
			;
			a
			))
		; if passed no argument, we're on the final step. Return the original val this gate was made from,
		 ; as it's a valid prime.
		([] x)))

(defn recursivePrimes
	[upperbound pipe value]
	(cond
		; we're done: return all of the gates
		(> value upperbound) (map #(%) pipe)
		;
		(boolean (reduce #(%2 %1) value pipe)) (recursivePrimes upperbound (conj pipe (moduLimit value)) (+ value 1))
		:else (recursivePrimes upperbound pipe (+ value 1))))

(defn -main
  "I don't do a whole lot."
  []
  (let [gate (moduLimit 2)]
  	(println (reduce #(str %1 ", " %2) (recursivePrimes 500 [] 2)))))
