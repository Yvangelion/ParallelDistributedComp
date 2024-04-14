import matplotlib.pyplot as plt

# Array sizes (y-axis)
array_sizes = [10, 100, 200, 300, 1000]

# Number of threads (x-axis)
num_threads = [1, 2, 4, 8, 16]

# effieincy data for each thread count
runtimes_thread_1= [1, 1, 1, 1, 1]
runtimes_thread_2 = [0.5, 0.5, 0.5, 1, 1]
runtimes_thread_4 = [0.25, 0.05, 0.25, 0.125, 0.25]
runtimes_thread_8 = [0.0625, 0.0625, 0.125, 0.0625, 0.25]
runtimes_thread_16 = [0.0333, 0.011, 0.0313, 0.208, 0.208]


# Plotting the data
plt.figure(figsize=(10, 6))

plt.plot(array_sizes, runtimes_thread_1, label='1 Thread', marker='o')
plt.plot(array_sizes, runtimes_thread_2, label='2 Threads', marker='o')
plt.plot(array_sizes, runtimes_thread_4, label='4 Threads', marker='o')
plt.plot(array_sizes, runtimes_thread_8, label='8 Threads', marker='o')
plt.plot(array_sizes, runtimes_thread_16, label='16 Threads', marker='o')

plt.title('Effect of Array Size and Threads on Runtime')
plt.xlabel('Array Size')
plt.ylabel('Runtime')
plt.xticks(array_sizes)
plt.legend()
plt.grid(True)
plt.show()


runtimes_thread_1 = [19, 29, 30, 35, 36]
runtimes_thread_2 = [20, 23, 27, 30, 30]
runtimes_thread_4 = [20, 35, 21, 30, 19]
runtimes_thread_8 = [15, 26, 28, 20, 29]
runtimes_thread_16 = [20, 31, 22, 19, 21]


runtimes_thread_1 = [1, 1, 1, 1, 1]
runtimes_thread_2 = [1, 1, 1, 2, 2]
runtimes_thread_4 =  [1, 0.2, 1, 0.5, 1]
runtimes_thread_8 = [0.5, 0.5, 1, 0.5, 2]
runtimes_thread_16 = [0.333, 0.166, 0.5, 0.333, 0.333]

runtimes_thread_1= [1, 1, 1, 1, 1]
runtimes_thread_2 = [0.5, 0.5, 0.5, 1, 1]
runtimes_thread_4 = [0.25, 0.05, 0.25, 0.125, 0.25]
runtimes_thread_8 = [0.0625, 0.0625, 0.125, 0.0625, 0.25]
runtimes_thread_16 = [0.0333, 0.011, 0.0313, 0.208, 0.208]
