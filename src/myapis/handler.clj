(ns myapis.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.util.response :refer [response] :as rr]
            [ring.util.response :refer [header] :as rh]
            [clojure.java.jdbc :as j]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))


(def harlem {:dbtype "postgresql"
             :dbname "harlem"
         :host "localhost"
         :user "evan"
         :password "em718650"})

(defn services-education [] (j/query harlem ["select * from services where serviceid = 'education'"]))
(defn services-food [] (j/query harlem ["select * from services where serviceid = 'food'"]))

(defn services-health [] (j/query harlem ["select * from services where serviceid = 'health'"]))
(defn services-jobs [] (j/query harlem ["select * from services where serviceid = 'jobs'"]))
(defn services-legal [] (j/query harlem ["select * from services where serviceid = 'legal'"]))
(defn services-recreation [] (j/query harlem ["select * from services where serviceid = 'recreation'"]))

(defn services-shelter [] (j/query harlem ["select * from services where serviceid = 'shelter'"]))




(defn education-querey [] (j/query harlem ["select * from education"]))
(defn food-querey [] (j/query harlem ["select * from food"]))
(defn health-querey [] (j/query harlem ["select * from health"]))
(defn jobs-querey [] (j/query harlem ["select * from jobs"]))
(defn legal-querey [] (j/query harlem ["select * from legal"]))
(defn recreation-querey [] (j/query harlem ["select * from recreation"]))
(defn shelter-querey [] (j/query harlem ["select * from shelter"]))

(defn resp-from-other-port [ourResp]
  (rh/header
   (rh/header (rr/response (ourResp))
              "Access-Control-Allow-Origin" "*")
   "Access-Control-Allow-Headers" "true"))


(defroutes app-routes
  (ANY "/" [] "Hello World")
  (ANY "/api/education" []
       (resp-from-other-port education-querey))
  (ANY "/api/food" []
       (resp-from-other-port food-querey))
  (ANY "/api/health" []
       (resp-from-other-port health-querey))
  (ANY "/api/jobs" []
       (resp-from-other-port jobs-querey))       
  (ANY "/api/legal" []
       (resp-from-other-port legal-querey))
  (ANY "/api/recreation" []
       (resp-from-other-port recreation-querey))
  (ANY "/api/shelter" [] 
       (resp-from-other-port shelter-querey))
  (ANY "/api/services/education" []
       (resp-from-other-port services-education))
  (ANY "/api/services/food" []
       (resp-from-other-port services-food))
  (GET "/api/services/health" []
       (resp-from-other-port services-health))
  (ANY "/api/services/jobs" []
       (resp-from-other-port services-jobs))       
  (ANY "/api/services/legal" []
       (resp-from-other-port services-legal))
  (ANY "/api/services/recreation" []
       (resp-from-other-port services-recreation))
  (ANY "/api/services/shelter" [] 
       (resp-from-other-port services-shelter))
  (ANY "/evan" []
       (rr/response {:name "Evan" :status "smiling"}))
  (route/not-found "Not Found"))
  
  (def app
    (-> app-routes
        (wrap-defaults site-defaults)
        (wrap-json-response)))

