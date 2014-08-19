(ns kixi.amon-schema-test
  (:require [kixi.amon-schema :refer :all]
            [clojure.test :refer :all]
            [schema.core :as s]))

(deftest validate-minimal-entities
  (testing "Validating entity_id only."
    (is (s/validate Entity {:entity_id "foo" :property_data {:wizz "bang"}})))
  (testing "Validating project_id property_code"
    (is (s/validate Entity {:project_id "foo" :property_code "bar" :property_data {:wizz "bang"}})))

  (testing "Validating no identifiers throws"
    (is (thrown? Exception (s/validate Entity {:property_data {:wizz "bang"}}))))
  (testing "Validating no property_code throws"
    (is (thrown? Exception (s/validate Entity {:project_id "foo" :property_data {:wizz "bang"}}))))
  (testing "Validating no project_id throws"
    (is (thrown? Exception (s/validate Entity {:property_code "bar" :property_data {:wizz "bang"}}))))

  (testing "Throws on rogue key"
    (is (thrown? Exception (s/validate Entity {:entity_id "bar" :does_not_belong 1 :property_data {:wizz "bang"}}))))
  )
