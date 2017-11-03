;;; math1.clj
;;;
;;; Leif Anderson November 28, 2016

;;; quatratic equation solver
;;; naive impl for testing
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
				((positive? (- (* b b) (* 4 a c)))
					(find-two-solutions a b c))
				(else '() )))

(defn solve-equation [a b c]
	(if (zero? a)
		(solve-linear-equation b c)
		(solve-quadratic-equation a b c)))
