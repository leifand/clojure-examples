;;; towers.clj - towers of hanoi
;;;
;;; June 13, 2018
;;; Leif Anderson
;;;
(defn towers [n source destination intermediate]
    (if (= n 1) 
        (println "Move disk " n " from " source " to " destination)
        (do 
            (towers (- n 1) source intermediate destination)
            (println "Move disk " n " from " source " to " destination)
            (towers (- n 1) intermediate destination source))))  

;;; test 
(time (towers 4 "A" "C" "B"))
;;;(time (towers 24 "A" "C" "B"))   