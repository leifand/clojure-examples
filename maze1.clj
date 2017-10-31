;;; maze-1.clj
;;;
;;; an amazing demonstration of Wilson's Algorithm
;;; from "Clojure Programming" - Emerik, Harper and Grand; O"Reily 2012

(ns la.maze)
(def time-stamp ^{:created (System/currentTimeMillis)}
		["Wilson's Algorithm"])

(defn maze [walls]
	(let [paths (reduce (fn [index [a b]] (merge-with into index {a [b] b [a]}))
			{} (map seq walls))
	      start-loc (rand-nth (keys paths))]
	(loop [walls walls unvisited (disj (set (keys paths)) start-loc)]
	   (if-let [loc (when-let [s (seq unvisited)] (rand-nth s))]
	      (let [walk (iterate (comp rand-nth paths) loc)
		    steps (zipmap (take-while unvisited walk) (next walk))]
		(recur (reduce disj walls (map set steps))
		   (reduce disj unvisited (keys steps))))
	       walls))))

(defn grid [w h]
	(set (concat
	   (for [i (range (dec w)) j (range h)] #{[i j] [(inc i) j]})
	   (for [i (range w) j (range (dec h))] #{[i j] [i (inc j)]}))))

;; old draw
;;(defn draw [w h maze] (doto (javax.swing.JFrame. "Maze") (.setContentPane
;;	(doto (proxy [javax.swing.JPanel] []
;;	   (paintComponent  [^java.awt.Graphics g]
;;	      (let [g (doto ^java.awt.Graphics2D (.create g)
;;	         (.scale 10 10)
;;		 (.translate 1.5 1.5)
;;		 (.setStroke (java.awt.BasicStroke. 0.4)))]
;;	   (.drawRect g -1 -1 w h)
;;	   (doseq [[[xa ya] [xb yb]] (map sort maze)]
;;	      (let [[xc yc] (if (= xa xb) [(dec xa) ya] [xa (dec ya)])]
;;		(.drawLine g xa ya xc yc))))))
;;	   (.setPreferredSize (java.awt.Dimension. (* 10 (inc w)) (* 10 (inc h))))))
;;	.pack
;;	(.setVisible true)))

;;;(draw 160 80 (maze (grid 160 80)))

(require '[clojure.zip :as z])
(defn ariadne-zip [labyrinth loc]
	(let [paths (reduce (fn [index [a b]] (merge-with into index {a [b] b [a]}))
	         {} (map seq labyrinth))
	      children (fn [[from to]] (seq (for [loc (paths to) :when (not= loc from)]
						 [to loc])))]
	(z/zipper (constantly true)
	   children
	   nil
	   [nil loc])))

(defn draw [w h maze path] (doto (javax.swing.JFrame. "MAZE")
				(.setContentPane
				   (doto (proxy [javax.swing.JPanel] []
				      (paintComponent [^java.awt.Graphics g]
				         (let [g (doto ^java.awt.Graphics2D (.create g)
				            (.scale 10 10)
				            (.translate 1.5 1.5)
				            (.setStroke (java.awt.BasicStroke. 0.4)))]
				            (.drawRect g -1 -1 w h)
				            (doseq [[[xa ya] [xb yb]] (map sort maze)]
				               (let [[xc yc] (if (= xa xb) [(dec xa) ya] [xa (dec ya)])]
					          (.drawLine g xa ya xc yc)))
					    (.translate g -0.5 -0.5)
					    (.setColor g java.awt.Color/RED)
					    (doseq [[[xa ya] [xb yb]] path] (.drawLine g xa ya xb yb)))))
					 (.setPreferredSize (java.awt.Dimension.
				            (* 10 (inc w)) (* 10 (inc h))))))
	.pack
	(.setVisible true)))

;;; test
(let [w 60
      h 60
      grid (grid w h)
      walls (maze grid)
      labyrinth (reduce disj grid walls)
      places (distinct (apply concat labyrinth))
      theseus (rand-nth places)
      minotaur (rand-nth places)
      path (->> theseus
		(ariadne-zip labyrinth)
		(iterate z/next)
		(filter #(= minotaur (first (z/node %))))
		first z/path rest)]
   (draw w h walls path))

;;; eof
