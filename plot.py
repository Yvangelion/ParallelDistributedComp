import matplotlib.pyplot as plt

# Test data
test_ids = [1, 2, 3, 4]
message_sizes_kb = [1, 5, 25, 50]
transmission_times_ms = [6, 14, 27, 64]
routing_table_lookup_times_ms = [2, 1, 1, 2]

# Plotting the data
plt.figure(figsize=(10, 6))

# Plot transmission times
plt.plot(message_sizes_kb, transmission_times_ms, marker='o', label='Transmission Time (ms)')

# Plot routing table lookup times
plt.plot(message_sizes_kb, routing_table_lookup_times_ms, marker='o', label='Routing Table Lookup Time (ms)')

# Adding labels and title
plt.xlabel('Message Size (KB)')
plt.ylabel('Time (ms)')
plt.title('Text File Test')
plt.legend()

# Display the graph
plt.grid(True)
plt.show()
