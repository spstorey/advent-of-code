package aoc2021.day12

class Path {

    List<String> routes = new ArrayList<>()
    boolean visitedSmallCaveTwice = false;

    Path(String input) {
        routes.add("start")
        String[] caves = input.split("-")
        if (caves[0] == "start") {
            routes.add(caves[1])
        } else {
            routes.add(caves[0])
        }
    }

    Path(String input, List<String> existingRoutes) {
        routes.addAll(existingRoutes)
        addLink(input)
    }

    String lastCave() {
        if (routes.size() > 0) {
            return routes.get(routes.size()-1)
        } else {
            return "?"
        }
    }

    boolean atEnd() {
        return lastCave() == "end"
    }

    boolean addLink(String link) {
        String[] caves = link.split("-")
        String lastCave = lastCave()
        if (caves[0] == lastCave) {
            //if (routes.get(routes.size() -2) != caves[1]) {
                routes.add(caves[1])
            //}
        } else if (caves[1] == lastCave) {
            //if (routes.get(routes.size() -2) != caves[0]) {
                routes.add(caves[0])
            //}
        }
    }

    boolean visitedSmallCaveOnce() {
        List<String> smallCaves = new ArrayList<>()
        for (String route : routes) {
            if (route.matches("[a-z]+")) {
                if (smallCaves.contains(route)) {
                    return true
                } else {
                    smallCaves.add(route)
                }
            }
        }
        return false
    }

    boolean visitedSmallCaveThreeTimes() {
        List<String> smallCaves = new ArrayList<>()
        for (String route : routes) {
            if (route.matches("[a-z]+")) {
                if (!smallCaves.contains(route)) {
                    smallCaves.add(route)
                } else if (visitedSmallCaveTwice) {
                    return true
                } else {
                    visitedSmallCaveTwice = true
                    smallCaves.add(route)
                }
            }
        }
        return false
    }
}
