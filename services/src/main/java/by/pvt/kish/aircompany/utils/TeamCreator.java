package by.pvt.kish.aircompany.utils;

import by.pvt.kish.aircompany.entity.Employee;
import by.pvt.kish.aircompany.entity.Plane;
import by.pvt.kish.aircompany.enums.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Kish Alexey
 */
public class TeamCreator {
    public static List<String> getPlanePositions(Plane plane) {
        List<String> positions = new ArrayList<>();
        Map<Position,Integer> team = plane.getTeam();
        for (int i = 0; i < team.get(Position.PILOT); i++) {
            positions.add(Position.PILOT.toString());
        }
        for (int i = 0; i < team.get(Position.NAVIGATOR); i++) {
            positions.add(Position.NAVIGATOR.toString());
        }
        for (int i = 0; i < team.get(Position.RADIOOPERATOR); i++) {
            positions.add(Position.RADIOOPERATOR.toString());
        }
        for (int i = 0; i < team.get(Position.STEWARDESS); i++) {
            positions.add(Position.STEWARDESS.toString());
        }
        return positions;
    }
}
