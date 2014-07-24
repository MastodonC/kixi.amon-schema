(ns kixi.amon-schema
  (:require [schema.core :as s]
            [schema-contrib.core :as sc]))

;;
;; Types
;;

(def Measurement
  (s/either
   {(s/required-key :type) s/Str
    (s/required-key :timestamp) s/Str
    (s/required-key :value) s/Str 
    (s/optional-key :error) s/Str}
   {(s/required-key :type) s/Str
    (s/required-key :timestamp) s/Str
    (s/required-key :error) s/Str
    (s/optional-key :value) s/Str}))

(def Reading
  {:type s/Str
   (s/optional-key :unit) s/Str
   (s/optional-key :resolution) s/Num
   (s/optional-key :accuracy) s/Num
   :period (s/enum "INSTANT" "CUMULATIVE" "PULSE")
   (s/optional-key :min) s/Num
   (s/optional-key :max) s/Num
   (s/optional-key :correction) s/Bool
   (s/optional-key :correctedUnit) s/Str
   (s/optional-key :correctionFactor) s/Num
   (s/optional-key :CorrectionFactorBreakdown) s/Str})

(def Readings
  [Reading])

(def LatLong
  {:latitude s/Num
   :longitude s/Num})

(def Location
  (s/either
   LatLong
   (merge {:name s/Str} LatLong)
   {:name s/Str}
   {}))

(def Dataset
  {:entity_id s/Str
   :operation s/Str
   :name s/Str
   :members [s/Str]})

(def Device
  {:entity_id s/Str
   :description s/Str
   :metadata {:passivrole s/Str}
   :readings [{:type s/Str
               :resolution s/Str ;;stringified number
               :accuracy s/Str ;;stringified number
               :period (s/enum "INSTANT" "PULSE" "CUMULATIVE")
               :unit s/Str
               (s/optional-key :user_metadata) {s/Str s/Str}}]})

(def Entity
  {:project_id s/Str
   :property_code s/Str
   :device_ids [s/Str]
   :metering_point_ids [s/Str]})

(def Project
  {:name s/Str
   :programme_id s/Str})

(def Programme
  {:name s/Str})
