package dao;

import model.AirportFlightStat;
import model.ClientBookingSummary;
import model.EmployeeFlightSummary;
import model.FlightSeatReport;
import util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewDAOImpl implements ViewDAO {

    @Override
    public List<FlightSeatReport> getFlightSeatReport() {
        List<FlightSeatReport> list = new ArrayList<>();
        String query = "SELECT * FROM view_flight_seat_availability";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new FlightSeatReport(
                        rs.getInt("flight_id"),
                        rs.getString("aircraft_model"),
                        rs.getInt("seat_capacity"),
                        rs.getInt("available_seats")
                ));
            }

        } catch (SQLException e) {
            System.err.println("(e) Error fetching report: " + e.getMessage());
        }

        return list;
    }

    @Override
    public List<ClientBookingSummary> getClientBookingSummary() {
        List<ClientBookingSummary> list = new ArrayList<>();
        String query = "SELECT * FROM view_client_booking_summary";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new ClientBookingSummary(
                        rs.getInt("client_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("total_bookings"),
                        rs.getDouble("total_spent"),
                        rs.getDouble("avg_ticket_price")
                ));
            }

        } catch (SQLException e) {
            System.err.println("(e) Error loading client summary: " + e.getMessage());
        }

        return list;
    }

    @Override
    public List<AirportFlightStat> getDepartureAirportFlightStats() {
        List<AirportFlightStat> stats = new ArrayList<>();
        String sql = "SELECT departure_airport, total_flights FROM view_flight_distribution_by_departure";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                stats.add(new AirportFlightStat(
                        rs.getString("departure_airport"),
                        rs.getInt("total_flights")
                ));
            }
        } catch (SQLException e) {
            System.err.println("(e) Error loading departure airport stats: " + e.getMessage());
        }

        return stats;
    }


    @Override
    public List<EmployeeFlightSummary> getEmployeeFlightSummary() {
        List<EmployeeFlightSummary> list = new ArrayList<>();
        String query = "SELECT * FROM view_employee_flight_summary";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new EmployeeFlightSummary(
                        rs.getInt("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("role_name"),
                        rs.getInt("total_flights")
                ));
            }

        } catch (SQLException e) {
            System.err.println("(e) Error loading employee summary: " + e.getMessage());
        }

        return list;
    }

}
