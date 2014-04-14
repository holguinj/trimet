(ns trimet.core
  (:require [clojure.string :as string]
            [clojure.data.json :as json])
  (:use [clojure.pprint]))

(defn new-stop-id-query-fn [app-id]
  "Returns a function that queries the Trimet API using the supplied appId."
  (let [query-head (str "http://developer.trimet.org/ws/V1/arrivals?appId=" app-id "&json=true&locIDs=")]
    (fn [& stops]
      (let [query-tail (string/join "," stops)
            query (str query-head query-tail)]
      (->> query
           (slurp)
           (json/read-str))))))

(defn next-arrivals [response route]
  (->> (get-in response ["resultSet" "arrival"])
       (filter #(= route (get % "route")))
       (map (juxt #(get % "fullSign") #(get % "estimated")))))
