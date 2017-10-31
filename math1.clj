;;; math1.clj
;;;
;;; Leif Anderson November 28, 2016

;;; quatratic equation solver
(defn solve-linear-equation [a b] (if (zero? a) '() (list (/ (- b) a))))
(defn solve-quadratic-equation [a b c] ())
(defn solve-equation [a b c] (if (zero? a) (solve-linear-equation b c)
					   (solve-quadratic-equation a b c)))
