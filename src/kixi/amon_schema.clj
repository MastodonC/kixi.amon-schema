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
   :timestamp s/Str ;; sc/ISO-Date-Time
   :value s/Str})

(def Measurement
  (s/either
   (merge
    BaseMeasurement
    {(s/optional-key :error) s/Str})
   (merge
    BaseMeasurement
    {:error s/Str
     (s/optional-key :value) s/Str})))

(def Measurements
  {:measurements [Measurement]})

(def BaseReading
  {:type s/Str
   (s/optional-key :resolution) s/Str
   (s/optional-key :accuracy) s/Str
   (s/optional-key :period) (s/enum "INSTANT" "PULSE" "CUMULATIVE")
   (s/optional-key :unit) s/Str
   (s/optional-key :user_metadata) {s/Any s/Any}
   (s/optional-key :alias) s/Str
   (s/optional-key :correction) s/Str
   (s/optional-key :corrected_unit) s/Str
   (s/optional-key :correction_factor) s/Str
   (s/optional-key :correction_factor_breakdown) s/Str
   (s/optional-key :frequency) s/Str
   (s/optional-key :max) s/Str
   (s/optional-key :median) s/Str
   (s/optional-key :min) s/Str
   (s/optional-key :status) s/Str
   (s/optional-key :synthetic) s/Bool
   (s/optional-key :user_id) s/Str
   (s/optional-key :actual_annual) s/Bool})

(def Reading
  (s/either
   BaseReading
   (merge
    BaseReading
    {(s/optional-key :upper_ts) s/Str ;; sc/ISO-Date-Time
     (s/optional-key :lower_ts) s/Str ;; sc/ISO-Date-Time
     })))

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
   (s/optional-key :location) Location
   (s/optional-key :metadata) {s/Keyword s/Any}
   (s/optional-key :readings) [Reading]
   (s/optional-key :name) s/Str
   (s/optional-key :privacy) s/Str
   (s/optional-key :metering_point_id) s/Str
   (s/optional-key :parent_id) s/Str
   (s/optional-key :synthetic) s/Bool})

(def Device
  (s/either
   BaseDevice
   (merge
    ;;BaseDevice but with Reading instead of BaseReading
    {:device_id s/Str}
    BaseDevice)))

(def profile-data-schema
  {(s/optional-key :profile_id) (s/maybe s/Str)
   (s/optional-key :event_type) (s/maybe s/Str)
   (s/optional-key :occupancy_under_18) (s/maybe s/Str)
   (s/optional-key :onsite_days_new_build) (s/maybe s/Str)
   (s/optional-key :flat_floor_heat_loss_type) (s/maybe s/Str)
   (s/optional-key :best_u_value_for_walls) (s/maybe s/Str)
   (s/optional-key :estimated_cost_new_build) (s/maybe s/Str)
   (s/optional-key :ter) (s/maybe s/Str)
   (s/optional-key :sap_version_year) (s/maybe s/Str)
   (s/optional-key :total_envelope_area) (s/maybe s/Str)
   (s/optional-key :sap_regulations_date) (s/maybe s/Str)
   (s/optional-key :multiple_glazing_area_percentage) (s/maybe s/Str)
   (s/optional-key :flat_floor_position) (s/maybe s/Str)
   (s/optional-key :modelling_software_methods_used) (s/maybe s/Str)
   (s/optional-key :co_heating_loss) (s/maybe s/Str)
   (s/optional-key :sap_assessor) (s/maybe s/Str)
   (s/optional-key :inadequate_heating) (s/maybe s/Str)
   (s/optional-key :profile_noise) (s/maybe s/Str)
   (s/optional-key :multiple_glazing_type) (s/maybe s/Str)
   (s/optional-key :sap_software) (s/maybe s/Str)
   (s/optional-key :profile_bus_summary_index) (s/maybe s/Str)
   (s/optional-key :thermal_bridging_strategy) (s/maybe s/Str)
   (s/optional-key :sealed_fireplaces) (s/maybe s/Str)
   (s/optional-key :flat_floors_in_block) (s/maybe s/Str)
   (s/optional-key :property_id) (s/maybe s/Str)
   (s/optional-key :air_tightness_assessor) (s/maybe s/Str)
   (s/optional-key :glazing_area_glass_only) (s/maybe s/Str)
   (s/optional-key :final_cost_new_build) (s/maybe s/Str)
   (s/optional-key :lighting_strategy) (s/maybe s/Str)
   (s/optional-key :fabric_energy_efficiency) (s/maybe s/Str)
   (s/optional-key :habitable_rooms) (s/maybe s/Str)
   (s/optional-key :profile_needs) (s/maybe s/Str)
   (s/optional-key :co_heating_assessor) (s/maybe s/Str)
   (s/optional-key :best_u_value_for_other) (s/maybe s/Str)
   (s/optional-key :renewable_contribution_heat) (s/maybe s/Str)
   (s/optional-key :total_area) (s/maybe s/Str)
   (s/optional-key :profile_temperature_in_summer) (s/maybe s/Str)
   (s/optional-key :draught_proofing_location) (s/maybe s/Str)
   (s/optional-key :heat_storage_present) (s/maybe s/Str)
   (s/optional-key :profile_productivity) (s/maybe s/Str)
   (s/optional-key :number_of_storeys) (s/maybe s/Str)
   (s/optional-key :passive_solar_strategy) (s/maybe s/Str)
   (s/optional-key :external_perimeter) (s/maybe s/Str)
   (s/optional-key :intervention_completion_date) (s/maybe s/Str)
   (s/optional-key :heat_loss_parameter_hlp) (s/maybe s/Str)
   (s/optional-key :electricity_storage_present) (s/maybe s/Str)
   (s/optional-key :roof_rooms_present) (s/maybe s/Str)
   (s/optional-key :primary_energy_requirement) (s/maybe s/Str)
   (s/optional-key :dwelling_u_value_other) (s/maybe s/Str)
   (s/optional-key :ventilation_approach) (s/maybe s/Str)
   (s/optional-key :construction_time_new_build) (s/maybe s/Str)
   (s/optional-key :draught_proofing) (s/maybe s/Str)
   (s/optional-key :frame_type) (s/maybe s/Str)
   (s/optional-key :appliances_strategy) (s/maybe s/Str)
   (s/optional-key :bedroom_count) (s/maybe s/Str)
   (s/optional-key :co_heating_equipment) (s/maybe s/Str)
   (s/optional-key :flat_heat_loss_corridor_other) (s/maybe s/Str)
   (s/optional-key :ber) (s/maybe s/Str)
   (s/optional-key :profile_image_to_visitors) (s/maybe s/Str)
   (s/optional-key :air_tightness_equipment) (s/maybe s/Str)
   (s/optional-key :innovation_approaches) (s/maybe s/Str)
   (s/optional-key :orientation) (s/maybe s/Str)
   (s/optional-key :total_budget_new_build) (s/maybe s/Str)
   (s/optional-key :best_u_value_for_floors) (s/maybe s/Str)
   (s/optional-key :completeness) (s/maybe s/Str)
   (s/optional-key :onsite_days) (s/maybe s/Str)
   (s/optional-key :water_saving_strategy) (s/maybe s/Str)
   (s/optional-key :airtightness_and_ventilation_strategy) (s/maybe s/Str)
   (s/optional-key :glazing_area_percentage) (s/maybe s/Str)
   (s/optional-key :occupant_change) (s/maybe s/Str)
   (s/optional-key :occupancy_total) (s/maybe s/Str)
   (s/optional-key :intention_ofpassvhaus) (s/maybe s/Str)
   (s/optional-key :profile_health) (s/maybe s/Str)
   (s/optional-key :occupancy_over_60) (s/maybe s/Str)
   (s/optional-key :annual_heating_load) (s/maybe s/Str)
   (s/optional-key :intervention_start_date) (s/maybe s/Str)
   (s/optional-key :profile_design) (s/maybe s/Str)
   (s/optional-key :gross_internal_area) (s/maybe s/Str)
   (s/optional-key :profile_air_in_winter) (s/maybe s/Str)
   (s/optional-key :intervention_description) (s/maybe s/Str)
   (s/optional-key :mains_gas) (s/maybe s/Str)
   (s/optional-key :profile_lightning) (s/maybe s/Str)
   (s/optional-key :multiple_glazing_type_other) (s/maybe s/Str)
   (s/optional-key :total_volume) (s/maybe s/Str)
   (s/optional-key :sap_version_issue) (s/maybe s/Str)
   (s/optional-key :profile_comfort) (s/maybe s/Str)
   (s/optional-key :heated_habitable_rooms) (s/maybe s/Str)
   (s/optional-key :open_fireplaces) (s/maybe s/Str)
   (s/optional-key :occupancy_18_to_60) (s/maybe s/Str)
   (s/optional-key :flat_length_sheltered_wall) (s/maybe s/Str)
   (s/optional-key :planning_considerations) (s/maybe s/Str)
   (s/optional-key :profile_bus_report_url) (s/maybe s/Str)
   (s/optional-key :design_guidance) (s/maybe s/Str)
   (s/optional-key :sap_rating) (s/maybe s/Str)
   (s/optional-key :overheating_cooling_strategy) (s/maybe s/Str)
   (s/optional-key :best_u_value_for_windows) (s/maybe s/Str)
   (s/optional-key :used_passivehaus_principles) (s/maybe s/Str)
   (s/optional-key :moisture_condensation_mould_strategy) (s/maybe s/Str)
   (s/optional-key :ventilation_approach_other) (s/maybe s/Str)
   (s/optional-key :sap_performed_on) (s/maybe s/Str)
   (s/optional-key :best_u_value_for_doors) (s/maybe s/Str)
   (s/optional-key :pipe_lagging) (s/maybe s/Str)
   (s/optional-key :renewable_contribution_elec) (s/maybe s/Str)
   (s/optional-key :controls_strategy) (s/maybe s/Str)
   (s/optional-key :conservation_issues) (s/maybe s/Str)
   (s/optional-key :annual_space_heating_requirement) (s/maybe s/Str)
   (s/optional-key :air_tightness_performed_on) (s/maybe s/Str)
   (s/optional-key :flat_heat_loss_corridor) (s/maybe s/Str)
   (s/optional-key :total_rooms) (s/maybe s/Str)
   (s/optional-key :space_heating_requirement) (s/maybe s/Str)
   (s/optional-key :multiple_glazing_u_value) (s/maybe s/Str)
   (s/optional-key :best_u_value_party_walls) (s/maybe s/Str)
   (s/optional-key :best_u_value_for_roof) (s/maybe s/Str)
   (s/optional-key :frame_type_other) (s/maybe s/Str)
   (s/optional-key :electricity_meter_type) (s/maybe s/Str)
   (s/optional-key :category) (s/maybe s/Str)
   (s/optional-key :cellar_basement_issues) (s/maybe s/Str)
   (s/optional-key :profile_air_in_summer) (s/maybe s/Str)
   (s/optional-key :co_heating_performed_on) (s/maybe s/Str)
   (s/optional-key :profile_temperature_in_winter) (s/maybe s/Str)
   (s/optional-key :air_tightness_rate) (s/maybe s/Str)
   (s/optional-key :footprint ) (s/maybe s/Str)})


(def window-set-schema
  {(s/optional-key :window_type) (s/maybe s/Str)
   (s/optional-key :frame_type) (s/maybe s/Str)
   (s/optional-key :frame_type_other) (s/maybe s/Str)
   (s/optional-key :percentage_glazing) (s/maybe s/Str)
   (s/optional-key :area) (s/maybe s/Str)
   (s/optional-key :location) (s/maybe s/Str)
   (s/optional-key :uvalue) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   })

(def thermal-images-schema
  {})

(def storey-schema
  {(s/optional-key :storey_type) (s/maybe s/Str)
   (s/optional-key :storey) (s/maybe s/Str)
   (s/optional-key :heat_loss_w_per_k) (s/maybe s/Str)
   (s/optional-key :heat_requirement_kwth_per_year) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   })

(def wall-schema
  {(s/optional-key :wall_type) (s/maybe s/Str)
   (s/optional-key :construction) (s/maybe s/Str)
   (s/optional-key :construction_other) (s/maybe s/Str)
   (s/optional-key :insulation) (s/maybe s/Str)
   (s/optional-key :insulation_date) (s/maybe s/Str)
   (s/optional-key :insulation_type) (s/maybe s/Str)
   (s/optional-key :insulation_thickness) (s/maybe s/Str)
   (s/optional-key :insulation_product) (s/maybe s/Str)
   (s/optional-key :uvalue) (s/maybe s/Str)
   (s/optional-key :location) (s/maybe s/Str)
   (s/optional-key :area) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   })


(def roof-schema
  {(s/optional-key :roof_type) (s/maybe s/Str)
   (s/optional-key :construction) (s/maybe s/Str)
   (s/optional-key :construction_other) (s/maybe s/Str)
   (s/optional-key :insulation_location_one) (s/maybe s/Str)
   (s/optional-key :insulation_location_one_other) (s/maybe s/Str)
   (s/optional-key :insulation_location_two) (s/maybe s/Str)
   (s/optional-key :insulation_location_two_other) (s/maybe s/Str)
   (s/optional-key :insulation_thickness_one) (s/maybe s/Str)
   (s/optional-key :insulation_thickness_one_other) (s/maybe s/Str)
   (s/optional-key :insulation_thickness_two) (s/maybe s/Str)
   (s/optional-key :insulation_thickness_two_other) (s/maybe s/Str)
   (s/optional-key :insulation_date) (s/maybe s/Str)
   (s/optional-key :insulation_type) (s/maybe s/Str)
   (s/optional-key :insulation_product) (s/maybe s/Str)
   (s/optional-key :uvalue) (s/maybe s/Str)
   (s/optional-key :uvalue_derived) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   })


(def floor-schema
  {(s/optional-key :floor_type) (s/maybe s/Str)
   (s/optional-key :construction) (s/maybe s/Str)
   (s/optional-key :construction_other) (s/maybe s/Str)
   (s/optional-key :insulation_thickness_one) (s/maybe s/Str)
   (s/optional-key :insulation_thickness_two) (s/maybe s/Str)
   (s/optional-key :insulation_type) (s/maybe s/Str)
   (s/optional-key :insulation_product) (s/maybe s/Str)
   (s/optional-key :uvalue) (s/maybe s/Str)
   (s/optional-key :uvalue_derived) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   })

(def roof-room-schema
  {(s/optional-key :location) (s/maybe s/Str)
   (s/optional-key :age) (s/maybe s/Str)
   (s/optional-key :insulation_placement) (s/maybe s/Str)
   (s/optional-key :insulation_thickness_one) (s/maybe s/Str)
   (s/optional-key :insulation_thickness_one_other) (s/maybe s/Str)
   (s/optional-key :insulation_thickness_two) (s/maybe s/Str)
   (s/optional-key :insulation_thickness_two_other) (s/maybe s/Str)
   (s/optional-key :insulation_date) (s/maybe s/Str)
   (s/optional-key :insulation_type) (s/maybe s/Str)
   (s/optional-key :insulation_product) (s/maybe s/Str)
   (s/optional-key :uvalue) (s/maybe s/Str)
   (s/optional-key :uvalue_derived) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   })


(def door-set-schema
  {(s/optional-key :door_type) (s/maybe s/Str)
   (s/optional-key :door_type_other) (s/maybe s/Str)
   (s/optional-key :frame_type) (s/maybe s/Str)
   (s/optional-key :frame_type_other) (s/maybe s/Str)
   (s/optional-key :percentage_glazing) (s/maybe s/Str)
   (s/optional-key :area) (s/maybe s/Str)
   (s/optional-key :location) (s/maybe s/Str)
   (s/optional-key :uvalue) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   })

(def extension-schema
  {(s/optional-key :age) (s/maybe s/Str)
   (s/optional-key :construction_date) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   })

(def conservatory-schema
  {(s/optional-key :conservatory_type) (s/maybe s/Str)
   (s/optional-key :area) (s/maybe s/Str)
   (s/optional-key :double_glazed) (s/maybe s/Str)
   (s/optional-key :glazed_perimeter) (s/maybe s/Str)
   (s/optional-key :height) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   })

(def wind-turbine-schema
  {(s/optional-key :turbine_type) (s/maybe s/Str)
   (s/optional-key :turbine_type_other) (s/maybe s/Str)
   (s/optional-key :make_model) (s/maybe s/Str)
   (s/optional-key :mcs_no) (s/maybe s/Str)
   (s/optional-key :inverter_type) (s/maybe s/Str)
   (s/optional-key :inverter_make_model) (s/maybe s/Str)
   (s/optional-key :inverter_mcs_no) (s/maybe s/Str)
   (s/optional-key :installer) (s/maybe s/Str)
   (s/optional-key :installer_mcs_no) (s/maybe s/Str)
   (s/optional-key :height_above_canopy) (s/maybe s/Str)
   (s/optional-key :commissioning_date) (s/maybe s/Str)
   (s/optional-key :capacity) (s/maybe s/Str)
   (s/optional-key :hub_height) (s/maybe s/Str)
   (s/optional-key :height_above_canpoy) (s/maybe s/Str)
   (s/optional-key :wind_speed) (s/maybe s/Str)
   (s/optional-key :wind_speed_info_source) (s/maybe s/Str)
   (s/optional-key :wind_speed_info_source_other) (s/maybe s/Str)
   (s/optional-key :est_annual_generation) (s/maybe s/Str)
   (s/optional-key :est_percentage_requirement_met) (s/maybe s/Str)
   (s/optional-key :est_percentage_exported) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   })

(def small-hydro-schema
  {(s/optional-key :hydro_type) (s/maybe s/Str)
   (s/optional-key :make_model) (s/maybe s/Str)
   (s/optional-key :mcs_no) (s/maybe s/Str)
   (s/optional-key :inverter_type) (s/maybe s/Str)
   (s/optional-key :inverter_make_model) (s/maybe s/Str)
   (s/optional-key :inverter_mcs_no) (s/maybe s/Str)
   (s/optional-key :installer) (s/maybe s/Str)
   (s/optional-key :installer_mcs_no) (s/maybe s/Str)
   (s/optional-key :commissioning_date) (s/maybe s/Str)
   (s/optional-key :capacity) (s/maybe s/Str)
   (s/optional-key :head_drop) (s/maybe s/Str)
   (s/optional-key :design_flow) (s/maybe s/Str)
   (s/optional-key :est_annual_generation) (s/maybe s/Str)
   (s/optional-key :est_percentage_requirement_met) (s/maybe s/Str)
   (s/optional-key :est_percentage_exported) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   })

(def photovoltaic-schema
  {(s/optional-key :percentage_roof_covered) (s/maybe s/Str)
   (s/optional-key :photovoltaic_type) (s/maybe s/Str)
   (s/optional-key :photovoltaic_type_other) (s/maybe s/Str)
   (s/optional-key :make_model) (s/maybe s/Str)
   (s/optional-key :mcs_no) (s/maybe s/Str)
   (s/optional-key :efficiency) (s/maybe s/Str)
   (s/optional-key :inverter_type) (s/maybe s/Str)
   (s/optional-key :inverter_make_model) (s/maybe s/Str)
   (s/optional-key :inverter_mcs_no) (s/maybe s/Str)
   (s/optional-key :installer) (s/maybe s/Str)
   (s/optional-key :installer_mcs_no) (s/maybe s/Str)
   (s/optional-key :commissioning_date) (s/maybe s/Str)
   (s/optional-key :capacity) (s/maybe s/Str)
   (s/optional-key :area) (s/maybe s/Str)
   (s/optional-key :orientation) (s/maybe s/Str)
   (s/optional-key :pitch) (s/maybe s/Str)
   (s/optional-key :est_annual_generation) (s/maybe s/Str)
   (s/optional-key :est_percentage_requirement_met) (s/maybe s/Str)
   (s/optional-key :est_percentage_exported) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :performance) (s/maybe s/Str)})

(def solar-thermal-schema
  {(s/optional-key :solar_type) (s/maybe s/Str)
   (s/optional-key :solar_type_other) (s/maybe s/Str)
   (s/optional-key :make_model) (s/maybe s/Str)
   (s/optional-key :mcs_no) (s/maybe s/Str)
   (s/optional-key :installer) (s/maybe s/Str)
   (s/optional-key :installer_mcs_no) (s/maybe s/Str)
   (s/optional-key :commissioning_date) (s/maybe s/Str)
   (s/optional-key :capacity) (s/maybe s/Str)
   (s/optional-key :area) (s/maybe s/Str)
   (s/optional-key :orientation) (s/maybe s/Str)
   (s/optional-key :pitch) (s/maybe s/Str)
   (s/optional-key :est_annual_generation) (s/maybe s/Str)
   (s/optional-key :est_percentage_requirement_met) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   })

(def heat-pump-schema
  {(s/optional-key :heat_pump_type) (s/maybe s/Str)
   (s/optional-key :make_model) (s/maybe s/Str)
   (s/optional-key :cop) (s/maybe s/Str)
   (s/optional-key :spf) (s/maybe s/Str)
   (s/optional-key :mcs_no) (s/maybe s/Str)
   (s/optional-key :installer) (s/maybe s/Str)
   (s/optional-key :installer_mcs_no) (s/maybe s/Str)
   (s/optional-key :commissioning_date) (s/maybe s/Str)
   (s/optional-key :heat_source_type) (s/maybe s/Str)
   (s/optional-key :heat_source_type_other) (s/maybe s/Str)
   (s/optional-key :depth) (s/maybe s/Str)
   (s/optional-key :geology) (s/maybe s/Str)
   (s/optional-key :capacity) (s/maybe s/Str)
   (s/optional-key :est_annual_generation) (s/maybe s/Str)
   (s/optional-key :est_percentage_requirement_met) (s/maybe s/Str)
   (s/optional-key :dhw) (s/maybe s/Str)
   (s/optional-key :est_percentage_dhw_requirement_met) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   })

(def biomass-schema
  {(s/optional-key :biomass_type) (s/maybe s/Str)
   (s/optional-key :model) (s/maybe s/Str)
   (s/optional-key :mcs_no) (s/maybe s/Str)
   (s/optional-key :installer) (s/maybe s/Str)
   (s/optional-key :installer_mcs_no) (s/maybe s/Str)
   (s/optional-key :commissioning_date) (s/maybe s/Str)
   (s/optional-key :capacity) (s/maybe s/Str)
   (s/optional-key :percentage_efficiency_from_spec) (s/maybe s/Str)
   (s/optional-key :est_annual_generation) (s/maybe s/Str)
   (s/optional-key :est_percentage_requirement_met) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   })

(def chp-schema
  {(s/optional-key :chp_type) (s/maybe s/Str)
   (s/optional-key :model) (s/maybe s/Str)
   (s/optional-key :mcs_no) (s/maybe s/Str)
   (s/optional-key :installer) (s/maybe s/Str)
   (s/optional-key :installer_mcs_no) (s/maybe s/Str)
   (s/optional-key :commissioning_date) (s/maybe s/Str)
   (s/optional-key :capacity_elec) (s/maybe s/Str)
   (s/optional-key :capacity_thermal) (s/maybe s/Str)
   (s/optional-key :est_annual_generation) (s/maybe s/Str)
   (s/optional-key :est_percentage_thermal_requirement_met) (s/maybe s/Str)
   (s/optional-key :est_percentage_exported) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   })

(def heating-system-schema
  {(s/optional-key :heating_type) (s/maybe s/Str)
   (s/optional-key :heat_source) (s/maybe s/Str)
   (s/optional-key :heat_transport) (s/maybe s/Str)
   (s/optional-key :heat_delivery) (s/maybe s/Str)
   (s/optional-key :heat_delivery_source) (s/maybe s/Str)
   (s/optional-key :efficiency_derivation) (s/maybe s/Str)
   (s/optional-key :boiler_type) (s/maybe s/Str)
   (s/optional-key :boiler_type_other) (s/maybe s/Str)
   (s/optional-key :fan_flue) (s/maybe s/Str)
   (s/optional-key :open_flue) (s/maybe s/Str)
   (s/optional-key :fuel) (s/maybe s/Str)
   (s/optional-key :heating_system) (s/maybe s/Str)
   (s/optional-key :heating_system_other) (s/maybe s/Str)
   (s/optional-key :heating_system_type) (s/maybe s/Str)
   (s/optional-key :heating_system_type_other) (s/maybe s/Str)
   (s/optional-key :heating_system_solid_fuel) (s/maybe s/Str)
   (s/optional-key :heating_system_solid_fuel_other) (s/maybe s/Str)
   (s/optional-key :bed_index) (s/maybe s/Str)
   (s/optional-key :make_and_model) (s/maybe s/Str)
   (s/optional-key :controls) (s/maybe s/Str)
   (s/optional-key :controls_other) (s/maybe s/Str)
   (s/optional-key :controls_make_and_model) (s/maybe s/Str)
   (s/optional-key :emitter) (s/maybe s/Str)
   (s/optional-key :trvs_on_emitters) (s/maybe s/Str)
   (s/optional-key :use_hours_per_week) (s/maybe s/Str)
   (s/optional-key :installer) (s/maybe s/Str)
   (s/optional-key :installer_engineers_name) (s/maybe s/Str)
   (s/optional-key :installer_registration_number) (s/maybe s/Str)
   (s/optional-key :commissioning_date) (s/maybe s/Str)
   (s/optional-key :inspector) (s/maybe s/Str)
   (s/optional-key :inspector_engineers_name) (s/maybe s/Str)
   (s/optional-key :inspector_registration_number) (s/maybe s/Str)
   (s/optional-key :inspection_date) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :efficiency) (s/maybe s/Str)})

(def hot-water-system-schema
  {(s/optional-key :dhw_type) (s/maybe s/Str)
   (s/optional-key :fuel) (s/maybe s/Str)
   (s/optional-key :fuel_other) (s/maybe s/Str)
   (s/optional-key :immersion) (s/maybe s/Str)
   (s/optional-key :cylinder_capacity) (s/maybe s/Str)
   (s/optional-key :cylinder_capacity_other) (s/maybe s/Str)
   (s/optional-key :cylinder_insulation_type) (s/maybe s/Str)
   (s/optional-key :cylinder_insulation_type_other) (s/maybe s/Str)
   (s/optional-key :cylinder_insulation_thickness) (s/maybe s/Str)
   (s/optional-key :cylinder_insulation_thickness_other) (s/maybe s/Str)
   (s/optional-key :cylinder_thermostat) (s/maybe s/Str)
   (s/optional-key :controls_same_for_all_zones) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   })

(def low-energy-light-schema
  {(s/optional-key :light_type) (s/maybe s/Str)
   (s/optional-key :light_type_other) (s/maybe s/Str)
   (s/optional-key :bed_index) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :proportion) (s/maybe s/Str)})

(def ventilation-system-schema
  {(s/optional-key :approach) (s/maybe s/Str)
   (s/optional-key :approach_other) (s/maybe s/Str)
   (s/optional-key :ventilation_type) (s/maybe s/Str)
   (s/optional-key :ventilation_type_other) (s/maybe s/Str)
   (s/optional-key :mechanical_with_heat_recovery) (s/maybe s/Str)
   (s/optional-key :manufacturer) (s/maybe s/Str)
   (s/optional-key :ductwork_type) (s/maybe s/Str)
   (s/optional-key :ductwork_type_other) (s/maybe s/Str)
   (s/optional-key :controls) (s/maybe s/Str)
   (s/optional-key :controls_other) (s/maybe s/Str)
   (s/optional-key :manual_control_location) (s/maybe s/Str)
   (s/optional-key :operational_settings) (s/maybe s/Str)
   (s/optional-key :operational_settings_other) (s/maybe s/Str)
   (s/optional-key :installer) (s/maybe s/Str)
   (s/optional-key :installer_engineers_name) (s/maybe s/Str)
   (s/optional-key :installer_registration_number) (s/maybe s/Str)
   (s/optional-key :commissioning_date) (s/maybe s/Str)
   (s/optional-key :total_installed_area) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   })

(def airflow-measurement-schema
  {(s/optional-key :reference) (s/maybe s/Str)
   (s/optional-key :system) (s/maybe s/Str)
   (s/optional-key :inspector) (s/maybe s/Str)
   (s/optional-key :inspector_engineers_name) (s/maybe s/Str)
   (s/optional-key :inspector_registration_number) (s/maybe s/Str)
   (s/optional-key :inspection_date) (s/maybe s/Str)
   (s/optional-key :created_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :updated_at) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :measured_low) (s/maybe s/Str)
   (s/optional-key :design_low) (s/maybe s/Str)
   (s/optional-key :measured_high) (s/maybe s/Str)
   (s/optional-key :design_high) (s/maybe s/Str)})

(def Profile
  {(s/optional-key :property_code) (s/maybe s/Str)
   (s/optional-key :entity_id) (s/maybe s/Str)
   (s/optional-key :profile_id) (s/maybe s/Str)
   (s/optional-key :project_id) (s/maybe s/Str)
   (s/optional-key :timestamp) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :profile_data) profile-data-schema
   (s/optional-key :window_sets) [window-set-schema]
   (s/optional-key :thermal_images) [thermal-images-schema]
   (s/optional-key :storeys) [storey-schema]
   (s/optional-key :walls) [wall-schema]
   (s/optional-key :roofs) [roof-schema]
   (s/optional-key :floors) [floor-schema]
   (s/optional-key :roof_rooms) [roof-room-schema]
   (s/optional-key :door_sets) [door-set-schema]
   (s/optional-key :extensions) [extension-schema]
   (s/optional-key :conservatories) [conservatory-schema]
   (s/optional-key :wind_turbines) [wind-turbine-schema]
   (s/optional-key :small_hydros) [small-hydro-schema]
   (s/optional-key :photovoltaics) [photovoltaic-schema]
   (s/optional-key :solar_thermals) [solar-thermal-schema]
   (s/optional-key :heat_pumps) [heat-pump-schema]
   (s/optional-key :biomasses) [biomass-schema]
   (s/optional-key :chps) [chp-schema]
   (s/optional-key :heating_systems) [heating-system-schema]
   (s/optional-key :hot_water_systems) [hot-water-system-schema]
   (s/optional-key :low_energy_lights) [low-energy-light-schema]
   (s/optional-key :ventilation_systems) [ventilation-system-schema]
   (s/optional-key :airflow_measurements) [airflow-measurement-schema]})

(def BaseEntity
  {:property_code (s/maybe s/Str)
   (s/optional-key :address_country) (s/maybe s/Str)
   (s/optional-key :address_county) (s/maybe s/Str)
   (s/optional-key :address_region) (s/maybe s/Str)
   (s/optional-key :address_street_two) (s/maybe s/Str)
   (s/optional-key :name) (s/maybe s/Str)
   (s/optional-key :retrofit_completion_date) (s/maybe s/Str) ;; sc/ISO-Date-Time
   (s/optional-key :user_id) (s/maybe s/Str)
   (s/optional-key :project_id) (s/maybe s/Str)
   (s/optional-key :profile_data_event_type) (s/maybe s/Str)

   (s/optional-key :property_data) {s/Keyword s/Str}

   (s/optional-key :csv_uploads) [s/Str]
   (s/optional-key :device_ids) [s/Str]
   (s/optional-key :devices) [Device]
   (s/optional-key :documents) [s/Str]
   (s/optional-key :metering_point_ids) [s/Str]
   (s/optional-key :notes) [s/Str]
   (s/optional-key :photos) [s/Str]
   (s/optional-key :profiles) [Profile]})

(def Entity
  (s/either
   BaseEntity
   (merge
    BaseEntity
    {:entity_id (s/maybe s/Str)
     (s/optional-key :calculated_fields_labels) {s/Any s/Str}
     (s/optional-key :calculated_fields_last_calc) {s/Any s/Str}
     (s/optional-key :calculated_fields_values) {s/Any s/Str}})))

(def BaseProject
  {:name s/Str
   :programme_id s/Str
   (s/optional-key :created_at) s/Str ;; sc/ISO-Date-Time
   (s/optional-key :description) s/Str
   (s/optional-key :organisation) s/Str
   (s/optional-key :project_code) s/Str
   (s/optional-key :project_type) s/Str
   (s/optional-key :type_of) s/Str
   (s/optional-key :updated_at) s/Str ;; sc/ISO-Date-Time
   (s/optional-key :user_id) s/Str})

(def Project
  (s/either
   BaseProject
   (merge
    BaseProject
    {:project_id s/Str
     (s/optional-key :properties) [Entity]})))

(def BaseProgramme
  {:name s/Str
   (s/optional-key :created_at) s/Str ;; sc/ISO-Date-Time
   (s/optional-key :description) s/Str
   (s/optional-key :home_page_text) s/Str
   (s/optional-key :lead_organisations) s/Str
   (s/optional-key :lead_page_text) s/Str
   (s/optional-key :leaders) s/Str
   (s/optional-key :public_access) s/Str
   (s/optional-key :updated_at) s/Str ;; sc/ISO-Date-Time
   (s/optional-key :user_id) s/Str})

(def Programme
  (s/either
   BaseProgramme
   (merge
    BaseProgramme
    {:programme_id s/Str
     (s/optional-key :projects) [Project]})))
