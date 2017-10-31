;;; hello2.clj
;;;
;;; Leif Anderson Novemeber 27, 2016

(def visitors (ref #{}))
(defn hello [username]
	(dosync
		(let [past-visitor (@visitors username)]
			(if past-visitor (str "Welcome back, " username)
			(do (alter visitors conj username)
					(str "Hello, " username))))))

(println "test visitors")
(hello "Leif")
(hello "Fred")
(hello "Leif")
(hello "Erich")
(hello "Fred")
(println "END: .... visitors ...")
