# Task 2 – Hospital Outpatient Appointment Desk

## 2.1 Event model

| Event source | Event object | Listener | Action |
|---|---|---|---|
| Department `JComboBox` | `ActionEvent` | `ActionListener` | Refresh doctor choices for selected department |
| Doctor `JComboBox` | `ActionEvent` | `ActionListener` | Clear or validate selected doctor/time slot |
| Time-slot `JComboBox` | `ActionEvent` | `ActionListener` | Store selected slot |
| Book button | `ActionEvent` | `ActionListener` | Validate fields and book appointment |
| Window close | `WindowEvent` | `WindowListener` / `WindowAdapter` | Ask before closing if booking is in progress |
| Confirm dialog | `ActionEvent` | `ActionListener` | Continue booking or cancel |

## 2.2 Component hierarchy

```text
JFrame (Appointment Desk) – BorderLayout
├── JPanel header – FlowLayout
│   └── JLabel title
├── JPanel form – GridBagLayout
│   ├── JLabel + JTextField patient ID
│   ├── JLabel + JComboBox department
│   ├── JLabel + JComboBox doctor
│   └── JLabel + JComboBox time slot
└── JPanel footer – BorderLayout
    ├── JLabel status (CENTER)
    └── JPanel buttons – FlowLayout (SOUTH)
        └── JButton Book
```

## 2.3 Handler pseudocode

**(a) Refresh doctor list when department changes**

```text
PROCEDURE departmentChanged
    selectedDepartment ← departmentCombo.getSelectedItem()
    doctorCombo.removeAllItems()
    doctorList ← doctorMap.get(selectedDepartment)
    FOR EACH doctor IN doctorList
        doctorCombo.addItem(doctor)
    END FOR
    IF doctorList is empty THEN
        statusLabel.setText("No doctors available")
    ELSE
        statusLabel.setText("Select a doctor and time slot")
    END IF
END PROCEDURE
```

**(b) Validate and book appointment**

```text
PROCEDURE bookAppointment
    patientId ← patientIdField.getText().trim()
    department ← departmentCombo.getSelectedItem()
    doctor ← doctorCombo.getSelectedItem()
    slot ← slotCombo.getSelectedItem()

    IF patientId is empty OR department is null OR doctor is null OR slot is null THEN
        show error dialog
        RETURN
    END IF

    bookingInProgress ← TRUE
    TRY
        bookingService.book(patientId, department, doctor, slot)
        statusLabel.setText("Appointment booked")
        bookingInProgress ← FALSE
    CATCH error
        statusLabel.setText("Booking failed")
        show error dialog
        bookingInProgress ← FALSE
    END TRY
END PROCEDURE
```

## 2.4 Event sequence and applet comparison

1. `main()` schedules GUI creation using `SwingUtilities.invokeLater`.
2. Constructor creates components, registers listeners and configures the frame.
3. `setVisible(true)` displays the window.
4. User selection generates an `ActionEvent`; the registered `actionPerformed` runs.
5. Clicking Book invokes validation and booking logic.
6. Closing the window generates a `WindowEvent`; `windowClosing` asks for confirmation when booking is in progress.
7. If closing is approved, `dispose()` releases the window resources.

An applet instead follows its container-managed lifecycle: `init()` initializes once, `start()` begins/resumes, `paint()` renders, `stop()` pauses, and `destroy()` releases resources. A standalone Swing frame is not governed by the applet lifecycle.

## Alternative concept and complexity

Loading once into `Map<String, List<Doctor>>` costs O(d + m) time and O(d + m) memory for d departments and m total doctors; a department change then performs an average O(1) map lookup plus O(k) work to populate k doctors in that department. Fetching on every change requires a database round trip and query cost each time (approximately O(log m + k) with a suitable index, plus network/database latency), but uses less in-memory doctor-list storage. The cached map is faster for repeated changes; database fetching can provide fresher data.
