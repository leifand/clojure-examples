;;; math1.clj
;;;
;;; Leif Anderson June 13, 2018
;;; quatratic equation solver
;;;
(defn disciminant [a b c]
	(- (* b b) (* 4 a c)))

(defn solve-linear-equation [a b]
	(if (zero? a)
		'()
		(list (/ (- b) a))))

(defn find-one-solution [a b]
	(list (/ (- b) (* 2 a))))

(defn find-two-solutions [a b c]
	(list (/ (+ (- b) (Math.sqrt (- (* b b) (* 4 a c))))
					(* 2 a))
	(list (/ (- (- b) (Math.sqrt (- (* b b) (* 4 a c))))
					(* 2 a))))

(defn solve-quadratic-equation [a b c]
	(cond ((zero? (- (* b b) (* 4 a c)))
					(find-one-solution a b))
				((> (- (* b b) (* 4 a c))0)
					(find-two-solutions a b c))
				( '() )))

(defn solve-equation [a b c]
	(if (zero? a)
		(solve-linear-equation b c)
		(solve-quadratic-equation a b c)))

;;; test
(solve-equation 25 5 3)