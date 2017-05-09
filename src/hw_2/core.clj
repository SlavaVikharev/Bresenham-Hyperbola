(ns hw-2.core
  (:require [quil.core :as q]))

(def dbl (partial * 2))

(defn draw-points [x0 y0 x y]
  (q/point x y)
  (q/point (- (dbl x0) x) y)
  (q/point (- (dbl x0) x) (- (dbl y0) y))
  (q/point x (- (dbl y0) y)))

(defn draw-hyperbola [& {:keys [a b c d]}]
  (loop [x (+ a (q/abs b))
         y c]
    (when (and (< x (q/width)) (< y (q/height)))
      (draw-points a c x y)
      (let [delta (-
                   (q/sq (/ (- (inc x) a) b))
                   (q/sq (/ (- (inc y) c) d))
                   1)]
        (if (neg? delta)
          (recur (inc x) y)
          (recur x (inc y)))))))

(defn draw []
  (q/background 255)
  (draw-hyperbola :a 500 :b -50 :c 500 :d -10))

(defn -main []
  (q/defsketch hw-2
    :size :fullscreen
    :draw draw
    :features [:resizable]))

