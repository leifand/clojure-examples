;;; math1.clj
;;;
;;; Leif Anderson November 28, 2016

;;; quatratic equation solver

(defn disciminant [a b c]
	(- (* b b) (* 4 a c)))

(defn solve-linear-equation [a b]
	(if (zero? a)
		'()
		(list (/ (- b) a))))

(defn find-one-solution [a b]
	(list (/ (- b) (* 2 a))))
	
(defn solve-quadratic-equation [a b c] ())

(defn solve-equation [a b c]
	(if (zero? a)
		(solve-linear-equation b c)
		(solve-quadratic-equation a b c)))
