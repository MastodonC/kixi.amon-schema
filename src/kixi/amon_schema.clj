(ns kixi.amon-schema
  (:require [schema.core :as s]))

;;
;; Types
;;

(def BaseMeasurement
  {:type s/Str
   :timestamp s/Str})

(def Measurement
  (s/either
   (merge
    BaseMeasurement
    {:value s/Str
     (s/optional-key :error) s/Str})
   (merge
    BaseMeasurement
    {:error s/Str
     (s/optional-key :value) s/Str)))

(def Measurements
  {:measurements [Measurement]})

(def Devices
  "A schema for devices."
  {:entity_id s/Str
   :description s/Str
   :metadata {:passivrole s/Str}
   :readings [{:type s/Str
               :resolution s/Str
               :accuracy s/Str
               :period (s/enum "INSTANT" "PULSE" "CUMULATIVE")
               :unit s/Str
               (s/optional-key :user_metadata) {s/Str s/Str}}]})

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

(def BaseDevice
  {:entity_id s/Str
   :description s/Str
   :metadata {:passivrole s/Str}
   :readings [{:type s/Str
               :resolution s/Str ;;stringified number
               :accuracy s/Str ;;stringified number
               :period (s/enum "INSTANT" "PULSE" "CUMULATIVE")
               :unit s/Str
               (s/optional-key :user_metadata) {s/Str s/Str}}]})

(def Device
  (s/either
   BaseDevice
   (merge {:deviceId s/Str} BaseDevice)))

(def BaseEntity
  {:project_id s/Str
   :property_code s/Str
   :device_ids [s/Str]
   :metering_point_ids [s/Str]})

(def Entity
  (s/either
   BaseEntity
   (merge {:entityId s/Str} BaseEntity)))

(def BaseProject
  {:name s/Str
   :programmeId s/Str})

(def Project
  (s/either
   BaseProject
   (merge
    BaseProject
    {:projectId s/Str})))

(def BaseProgramme
  {:name s/Str})

(def Programme
  (s/either
   BaseProgramme
   (merge
    BaseProgramme
    {:programmeId s/Str})))
