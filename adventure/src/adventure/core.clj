(import '(java.util.regex Matcher))

(ns adventure.core
  (:use [clojure.core] :reload)
  (:require [clojure.data.json :as json]
            [clojure.string :as str]
            [clojure.java.io]
            [clojure.core.match :refer [match]]
            [clojure.set :refer [intersection subset? rename-keys]]) (:gen-class))

(defn quit []
  (System/exit 0))

(def init-adventurer
  (let [map
     (json/read-str (slurp "gameLocations.json")
     :key-fn keyword)]
    {:location :Cleveland
     :hp 10
     :inventory #{}
     :movements 0
     :requirements {}
     :lives 3
     :rewards 0
     :tick 0
     :map map
     :seen #{}}))

(defn status [state]
  (let [location (state :location)]
    (print (str "You are " (-> state :map location :title) ". "))
    (when-not ((state [:adventurer :seen] state) location)
      (print (-> state :map location :desc)))
    (update-in state [:adventurer :seen] #(conj % location))))

(defn go [dir state]
  (let [location (state :location)
        dest (-> state :map location :dir dir keyword)]
          (if (nil? dest) (do (println "You can't go that way.") state)
           (assoc-in state [:location] dest))))

(defn Request [quest player]
  (let [key (keyword quest), movement (:map key)]
  (if (and movement (:requirements key not))
      (let [target (update-in player [:requirements] #(assoc % key (:map key)))] target) player)))

(defn look [player]
  (let [target (update-in player [:seen] #(disj % (-> player :location name))) _ (let [placesToGo (-> player :map ((player :location)) :thingsToGet),
                orients (-> player :map ((player :location)) :dir)]
            (do (when placesToGo (do (print (str "There are some items in this room: ")) (println placesToGo)))
            (when orients (do (print (str "You can go to other places: ")) (println orients)))))] target))

(defn respond [player requirement]
  (let [player (let [person (:lives player)] (update-in player [:tick] inc))] (match requirement
      [(:or :n :north)] (go :north player) [(:or :s :south)] (go :south player) [(:or :w :west)] (go :west player) [(:or :e :east)] (go :east player)
      [(:or :look)] (look player)
      [(:or :requirements)] (do (doseq [x (-> player :requirements)] (println x)) player)
      ([(:or :pick) & r] :seq) (let [thing (apply str (interpose "-" (map name r)))]
      (if (not ((player :inventory) thing))
      (let [placesToGo
        (let [current (player :location)] (-> player :map current :thingsToGet)) index (.indexOf placesToGo thing)]
      (if (not (= index -1))
      (let [current (update-in player
        (let [current (player :location)] [:map current :thingsToGet]) #(remove (fn [x] (= thing x)) %))
              current (update-in current [:inventory] #(conj % thing))]
          (let [thingsYouHave (-> player :map :items ((keyword thing)) :requirement)]
      (if thingsYouHave (Request thingsYouHave current) current))) (do (println (str thing " is not available.")) player))) (do player)))
      [:quit] (quit)
      [(:or :i :inventory)] (do (println "The inventories are :") (println (str (:inventory player))) player)
      ([(:or :look :examine) & r] :seq) (let [item (apply str (interpose "-" (map name r)))]
      (when (contains? (player :inventory) item) (prn (-> player :map :items ((keyword item)) :desc))) player) _ (let [thingsToDo (let [arr (set (map name requirement)),
      eventsToDo (-> player :map ((player :location)) :events), have (fn [toDo]
        (let [keys (:answer (val toDo))]
          (-> (intersection (set keys) arr) empty? not
            (and (-> toDo val :require set (subset? (-> player :inventory)))))))]
        (let [ToReturn (first (filter have eventsToDo))] (when ToReturn (-> ToReturn val))))]
          (if thingsToDo
            (let [ret (:moves thingsToDo), arr (map (fn [event] (eval (read-string (str "(fn [player] (update-in player " event "))")))) ret),
                player (reduce #(%2 %1) (conj arr player)), toReturn (:toReturn thingsToDo), _ (when toReturn (prn toReturn)), player (let [quest (thingsToDo :finished)]
          (if quest (let [key (keyword quest), movement (-> player :map :teams key)]
          (if (and movement (-> player :requirements key)) (do (update-in player [:requirements] #(dissoc % key))) player)) player)),
                player (let [quest (thingsToDo :requirement)]
          (if quest (Request quest player) player))] player) (do (println "Try again") player))))))

(defn -main
  "Initialize the adventure"
  [& args]
  (loop [local-state (Request (:map init-adventurer) init-adventurer)]
    (when-not (-> local-state :won)
      (let [pl (status local-state),
            _ (println "Consider! What is your next movement? "),
            requirement (read-line)]
        (recur (respond pl (mapv keyword (str/split requirement #"[.,?! ]+"))))))))
