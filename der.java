import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Event {
    private String name;
    private LocalDateTime dateTime;
    private String description;
    private List<String> attendees;

    public Event(String name, LocalDateTime dateTime, String description) {
        this.name = name;
        this.dateTime = dateTime;
        this.description = description;
        attendees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void addAttendee(String attendee) {
        attendees.add(attendee);
    }

    public List<String> getAttendees() {
        return attendees;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Event: " + name + "\nDate and Time: " + dateTime.format(formatter) + "\nDescription: " + description;
    }

    // Generate random events
    // Generate random events
    static Event generateRandomEvent() {
        Random random = new Random();
        String[] names = {"Meeting", "Appointment", "Conference", "Party", "Workshop"};
        String[] descriptions = {"Discuss project progress", "Meet with clients", "Attend industry conference", "Celebrate a special occasion", "Learn new skills"};

        String name = names[random.nextInt(names.length)];
        LocalDateTime dateTime = LocalDateTime.now().plusDays(random.nextInt(30)).plusHours(random.nextInt(24)).plusMinutes(random.nextInt(60));
        String description = descriptions[random.nextInt(descriptions.length)];

        return new Event(name, dateTime, description);
    }
}
class RecurrencePattern {
    private int interval; // e.g., 1 for daily, 7 for weekly
    private LocalDateTime endDateTime;

    public RecurrencePattern(int interval, LocalDateTime endDateTime) {
        this.interval = interval;
        this.endDateTime = endDateTime;
    }

    public LocalDateTime getNextDateTime(LocalDateTime currentDateTime) {
        return currentDateTime.plusDays(interval);
    }
}

    class Calendar {
        private List<Event> events;

        public Calendar() {
            events = new ArrayList<>();
        }

        public void addEvent(Event event) {
            events.add(event);
            System.out.println("Event added successfully.");
        }

        public void updateEvent(String name, LocalDateTime dateTime, String description) {
            Event event = findEventByName(name);
            if (event != null) {
                event = new Event(name, dateTime, description);
                events.remove(event);
                events.add(event);
                System.out.println("Event updated successfully.");
            } else {
                System.out.println("Event not found.");
            }
        }

        public void deleteEvent(String name) {
            Event event = findEventByName(name);
            if (event != null) {
                events.remove(event);
                System.out.println("Event deleted successfully.");
            } else {
                System.out.println("Event not found.");
            }
        }

        public void listEvents(LocalDateTime startDateTime, LocalDateTime endDateTime) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            System.out.println("Events between " + startDateTime.format(formatter) + " and " + endDateTime.format(formatter) + ":");
            for (Event event : events) {
                if (event.getDateTime().isAfter(startDateTime) && event.getDateTime().isBefore(endDateTime)) {
                    System.out.println(event);
                }
            }
        }


        public void setReminder(String name) {
            Event event = findEventByName(name);
            if (event != null) {
                // Add your reminder logic here
                System.out.println("Reminder set for event: " + name);
            } else {
                System.out.println("Event not found.");
            }
        }

        public void addRecurringEvent(String name, LocalDateTime startDateTime, LocalDateTime endDateTime, String description, RecurrencePattern pattern) {
            LocalDateTime currentDateTime = startDateTime;
            while (currentDateTime.isBefore(endDateTime)) {
                Event event = new Event(name, currentDateTime, description);
                events.add(event);
                currentDateTime = pattern.getNextDateTime(currentDateTime);
            }
            System.out.println("Recurring events added successfully.");
        }

        public void addAttendee(String name, String attendee) {
            Event event = findEventByName(name);
            if (event != null) {
                event.addAttendee(attendee);
                System.out.println("Attendee added to event: " + name);
            } else {
                System.out.println("Event not found.");
            }
        }

        public Event findEventByName(String name) {
            for (Event event : events) {
                if (event.getName().equalsIgnoreCase(name)) {
                    return event;
                }
            }
            return null;
        }

        public List<Event> getEvents() {
            return events;

        }

    }

        public class der {
            public static void main(String[] args) {
                Calendar calendar = new Calendar(); // Create an instance of the Calendar class

                Scanner scanner = new Scanner(System.in);

                while (true) {
                    System.out.println("\nCalendar Application Menu:");
                    System.out.println("1. Add Event");
                    System.out.println("2. Update Event");
                    System.out.println("3. Delete Event");

                    System.out.println("4. List Events");
                    System.out.println("5. Add Attendes");
                    System.out.println("6. View Attendes");
                    System.out.println("7. update attendes");
                    System.out.println("8. delete attendes");
                    System.out.println("9. Search Event");
                    System.out.println("10. keyword to search events");
                    System.out.println("11. Add random event");
                    System.out.println("12. Set reminder");
                    System.out.println("13. Add recurring event");
                    System.out.println("14. Exit");
                    System.out.print("Enter your choice (1-13): ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter event name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter event date and time (yyyy-MM-dd HH:mm): ");
                            LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                            System.out.print("Enter event description: ");
                            String description = scanner.nextLine();
                            Event event = new Event(name, dateTime, description);
                            calendar.addEvent(event);
                            break;
                        case 2:
                            System.out.print("Enter event name to update: ");
                            String updateName = scanner.nextLine();
                            System.out.print("Enter new event date and time (yyyy-MM-dd HH:mm): ");
                            LocalDateTime updateDateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                            System.out.print("Enter new event description: ");
                            String updateDescription = scanner.nextLine();
                            calendar.updateEvent(updateName, updateDateTime, updateDescription);
                            break;
                        case 3:
                            System.out.print("Enter event name to delete: ");
                            String deleteName = scanner.nextLine();
                            calendar.deleteEvent(deleteName);
                            break;
                        case 4:
                            System.out.print("Enter start date and time (yyyy-MM-dd HH:mm): ");
                            LocalDateTime startDateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                            System.out.print("Enter end date and time (yyyy-MM-dd HH:mm): ");
                            LocalDateTime endDateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                            calendar.listEvents(startDateTime, endDateTime);
                            break;
                        case 5:
                            System.out.print("Enter event name to add attendee: ");
                            String attendeeEventName = scanner.nextLine();
                            System.out.print("Enter attendee name: ");
                            String attendeeName = scanner.nextLine();
                            calendar.addAttendee(attendeeEventName, attendeeName);
                            break;
                        case 6:
                            System.out.print("Enter event name to view attendees: ");
                            String viewAttendeesEventName = scanner.nextLine();
                            Event viewAttendeesEvent = calendar.findEventByName(viewAttendeesEventName);
                            if (viewAttendeesEvent != null) {
                                List<String> attendees = viewAttendeesEvent.getAttendees();
                                if (!attendees.isEmpty()) {
                                    System.out.println("Attendees for event '" + viewAttendeesEventName + "':");
                                    for (String attendee : attendees) {
                                        System.out.println(attendee);
                                    }
                                } else {
                                    System.out.println("No attendees found for event '" + viewAttendeesEventName + "'.");
                                }
                            } else {
                                System.out.println("Event not found.");
                            }
                            break;
                        case 7:
                            System.out.print("Enter event name to update attendees: ");
                            String updateAttendeesEventName = scanner.nextLine();
                            Event updateAttendeesEvent = calendar.findEventByName(updateAttendeesEventName);
                            if (updateAttendeesEvent != null) {
                                System.out.print("Enter attendee name to add: ");
                                String newAttendee = scanner.nextLine();
                                updateAttendeesEvent.addAttendee(newAttendee);
                                System.out.println("Attendee added to event '" + updateAttendeesEventName + "'.");
                            } else {
                                System.out.println("Event not found.");
                            }
                            break;
                        case 8:
                            System.out.print("Enter event name to delete attendee from: ");
                            String deleteAttendeeEventName = scanner.nextLine();
                            Event deleteAttendeeEvent = calendar.findEventByName(deleteAttendeeEventName);
                            if (deleteAttendeeEvent != null) {
                                System.out.print("Enter attendee name to delete: ");
                                String attendeeToDelete = scanner.nextLine();
                                List<String> attendees = deleteAttendeeEvent.getAttendees();
                                if (attendees.contains(attendeeToDelete)) {
                                    attendees.remove(attendeeToDelete);
                                    System.out.println("Attendee '" + attendeeToDelete + "' deleted from event '" + deleteAttendeeEventName + "'.");
                                } else {
                                    System.out.println("Attendee not found in event '" + deleteAttendeeEventName + "'.");
                                }
                            } else {
                                System.out.println("Event not found.");
                            }
                            break;
                        case 9:
                            System.out.print("Enter attendee name to search events: ");
                            String attendeeToSearch = scanner.nextLine();
                            List<Event> eventsWithAttendee = new ArrayList<>();
                            for (Event events : calendar.getEvents()) {
                                if (events.getAttendees().contains(attendeeToSearch)) {
                                    eventsWithAttendee.add(events);
                                }
                            }
                            if (!eventsWithAttendee.isEmpty()) {
                                System.out.println("Events attended by '" + attendeeToSearch + "':");
                                for (Event events : eventsWithAttendee) {
                                    System.out.println(events);
                                }
                            } else {
                                System.out.println("No events found for attendee '" + attendeeToSearch + "'.");
                            }
                            break;
                        case 10:
                            System.out.print("Enter keyword to search events: ");
                            String keyword = scanner.nextLine();
                            List<Event> eventsWithKeyword = new ArrayList<>();
                            for (Event events : calendar.getEvents()) {
                                if (events.getName().toLowerCase().contains(keyword.toLowerCase()) || events.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                                    eventsWithKeyword.add(events);
                                }
                            }
                            if (!eventsWithKeyword.isEmpty()) {
                                System.out.println("Events matching keyword '" + keyword + "':");
                                for (Event events : eventsWithKeyword) {
                                    System.out.println(events);
                                }
                            } else {
                                System.out.println("No events found matching keyword '" + keyword + "'.");
                            }
                            break;


                        case 11:
                            Event randomEvent = Event.generateRandomEvent();
                            calendar.addEvent(randomEvent);
                            System.out.println("Random event added successfully.");
                            System.out.println("Event Name: " + randomEvent.getName());
                            System.out.println("Event Description: " + randomEvent.getDescription());
                            break;


                        case 12:
                            System.out.print("Enter event name to set reminder: ");
                            String reminderName = scanner.nextLine();
                            calendar.setReminder(reminderName);
                            break;
                        case 13:
                            System.out.print("Enter event name: ");
                            String recurringName = scanner.nextLine();
                            System.out.print("Enter start date and time (yyyy-MM-dd HH:mm): ");
                            LocalDateTime recurringStartDateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                            System.out.print("Enter end date and time (yyyy-MM-dd HH:mm): ");
                            LocalDateTime recurringEndDateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                            System.out.print("Enter interval (in days): ");
                            int interval = scanner.nextInt();
                            scanner.nextLine();
                            RecurrencePattern pattern = new RecurrencePattern(interval, recurringEndDateTime);
                            calendar.addRecurringEvent(recurringName, recurringStartDateTime, recurringEndDateTime, "", pattern);
                            break;

                        case 14:
                            System.out.println("Exiting calendar application.");
                            scanner.close();
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
        }



