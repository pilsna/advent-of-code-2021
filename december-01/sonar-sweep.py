# Day 1 - part 1
with open('sonar-sweep.txt') as textfile:
    measurements = textfile.readlines()
    previous_measurement = -1
    count = -1  # adjust for first count with no previous measurement
    for line in measurements:
        measurement = int(line.rstrip())
        if measurement > previous_measurement:
            count += 1
        previous_measurement = measurement
    print('count', count)

# Day 1 - part 2
with open('sonar-sweep.txt') as textfile:
    measurements = textfile.readlines()
    windows = []
    for index in range(0, len(measurements) - 2): # remove the last two incomplete windows
        window_sum = int(measurements[index]) + int(measurements[index + 1]) + int(measurements[index + 2])
        windows.append(window_sum)
    previous_window = -1
    count = -1  # adjust for first count with no previous window
    for window in windows:
        if window > previous_window:
            count += 1
        previous_window = window
    print('count', count)
