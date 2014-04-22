(ns kixi.amon-schema
  (:require [schema.core :as s]
            [schema-contrib.core :as sc]))

;;
;; Types
;;

(def BaseMeasurement
  {:type s/Str
   :timestamp sc/ISO-Date-Time})

(def Measurement
  (s/either
   (merge
    BaseMeasurement
    {:value s/Num
     (s/optional-key :aggregated) s/Bool})
   (merge
    BaseMeasurement
    {:error s/Str})))

(def Measurements
  {:measurements [Measurement]})

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
  {:entityId s/Str
   (s/optional-key :parentId) s/Str
   (s/optional-key :description) s/Str
   ;; (s/optional-key :meteringPointId) s/Str
   :privacy (s/enum "private" "public")
   (s/optional-key :location) Location
   (s/optional-key :metadata) {s/Keyword s/Any}
   (s/optional-key :readings) Readings
   (s/optional-key :measurements) Measurements})

(def Device
  (s/either
   BaseDevice
   (merge {:deviceId s/Str} BaseDevice)))

(def BaseEntity
  {:propertyCode s/Str
   :projectId s/Str
   (s/optional-key :deviceIds) [s/Str]
   ;; (s/optional-key :meteringPointIds) [s/Str] ;; would like to remove
   })

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
