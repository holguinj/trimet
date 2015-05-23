(ns trimet.core
  (:require [clojure.string :as string]
            [clojure.data.json :as json])
  (:use [clojure.pprint]))

(defn get-stop
  "Queries the Trimet API using the supplied appId."
  [app-id stop]
  (let [query-head (str "http://developer.trimet.org/ws/V1/arrivals?appId=" app-id "&json=true&locIDs=")]
    (let [query (str query-head stop)]
      (->> query
        (slurp)
        (json/read-str)))))

(defn next-arrivals [response route]
  (->> (get-in response ["resultSet" "arrival"])
       (filter #(= route (get % "route")))
       (map (juxt #(get % "fullSign") #(get % "estimated")))))

(defn get-arrivals
  "Gets the next arrivals for the given stop and bus."
  [id stop bus]
  (-> (get-stop id stop)
    (next-arrivals bus)))
