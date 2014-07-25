(ns kixi.amon-schema
  (:require [schema.core :as s]
            [schema-contrib.core :as sc]))

;;
;; Types
;;

(def Dataset
  {:entity_id s/Str
   :operation (s/enum "sum" "subtract" "divide")
   :name s/Str
   :members [s/Str]})

(def BaseMeasurement
  {:type s/Str
   :timestamp sc/ISO-Date-Time})

(def Measurement
  (s/either
   (merge
    BaseMeasurement
    {:value s/Str
     (s/optional-key :error) s/Str})
   (merge
    BaseMeasurement
    {:error s/Str
     (s/optional-key :value) s/Str})))

(def Measurements
  {:measurements [Measurement]})

(def BaseReading
  {:type s/Str
   :resolution s/Str
   :accuracy s/Str
   :period (s/enum "INSTANT" "PULSE" "CUMULATIVE")
   :unit s/Str
   (s/optional-key :user_metadata) {s/Str s/Str}})

(def Reading
  (s/either
   BaseReading
   (merge BaseReading
          {(s/optional-key :alias) s/Str
           (s/optional-key :actual_annual) s/Str
           (s/optional-key :correction) s/Str
           (s/optional-key :corrected_unit) s/Str
           (s/optional-key :correction_factor) s/Str
           (s/optional-key :correction_factor_breakdown) s/Str
           (s/optional-key :frequency) s/Str
           (s/optional-key :max) s/Str
           (s/optional-key :median) s/Str
           (s/optional-key :min) s/Str
           (s/optional-key :status) s/Str
           (s/optional-key :synthetic) s/Str
           (s/optional-key :user_id) s/Str})))

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
   :readings [BaseReading]})

(def Device
  (s/either
   BaseDevice
   {:deviceId s/Str
    :entity_id s/Str
    :description s/Str
    :metadata {:passivrole s/Str}
    :readings [Reading]}))

(def BaseEntity
  {:project_id s/Str
   :property_code s/Str
   :device_ids [s/Str]
   :metering_point_ids [s/Str]})

(def Entity
  (s/either
   BaseEntity
   (merge 
    BaseEntity
    {:entityId s/Str
     (s/optional-key :address_country) s/Str
     (s/optional-key :address_county) s/Str
     (s/optional-key :address_region) s/Str
     (s/optional-key :address_street_two) s/Str
     (s/optional-key :calculated_fields_labels) {s/Str s/Str}
     (s/optional-key :calculated_fields_last_calc) {s/Str s/Str}
     (s/optional-key :calculated_fields_values) {s/Str s/Str}
     (s/optional-key :csv_uploads) [s/Str]
     (s/optional-key :documents) [s/Str]
     (s/optional-key :name) s/Str
     (s/optional-key :notes) [s/Str]
     (s/optional-key :photos) [s/Str]
     (s/optional-key :property_data) s/Str
     (s/optional-key :retrofit_completion_date) s/Str
     (s/optional-key :user_id) s/Str})))

(def BaseProject
  {:name s/Str
   :programmeId s/Str})

(def Project
  (s/either
   BaseProject
   (merge
    BaseProject
    {:projectId s/Str
     (s/optional-key :created_at) s/Str
     (s/optional-key :description) s/Str
     (s/optional-key :organisation) s/Str
     (s/optional-key :project_code) s/Str
     (s/optional-key :project_type) s/Str
     (s/optional-key :type_of) s/Str
     (s/optional-key :updated_at) s/Str
     (s/optional-key :user_id) s/Str})))

(def BaseProgramme
  {:name s/Str})

(def Programme
  (s/either
   BaseProgramme
   (merge
    BaseProgramme
    {:programmeId s/Str
     (s/optional-key :created_at) s/Str
     (s/optional-key :description) s/Str
     (s/optional-key :home_page_text) s/Str
     (s/optional-key :lead_organisations) s/Str
     (s/optional-key :lead_page_text) s/Str
     (s/optional-key :leaders) s/Str
     (s/optional-key :public_access) s/Str
     (s/optional-key :updated_at) s/Str
     (s/optional-key :user_id) s/Str})))
