from math import sqrt

from sklearn.neural_network import MLPRegressor

from FileReader import FileReader

nr_of_records = 1540


def find_solution_and_errors(predicted: [], reference: []):
    errors = []
    for i in range(len(predicted)):
        tmp_x = reference[i][0] - predicted[i][0]
        tmp_y = reference[i][1] - predicted[i][1]
        errors.append(sqrt(tmp_x ** 2 + tmp_y ** 2))
        print(i, "predicted:", predicted[i], "reference: ", reference[i], " error:", errors[i])
    return errors


def convert_to_2d_array(predicted_1d: []):
    result = []
    for i in range(len(predicted_1d)):
        tmp = str(predicted_1d[i])[1:-1].split(" ")  # [1975.55791897   4168.3236049 ] - przykladowy element tablicy
        remove_empty_elements(tmp)
        result.append([float(tmp[0]), float(tmp[1])])
    return result


def remove_empty_elements(tab: []):
    while tab.__len__() > 2:
        try:
            tab.remove('')
        except ValueError:
            pass


if __name__ == "__main__":
    print('hello world')
    fp = FileReader()
    train, train_ref, test, test_ref = fp.read_files()

    # mlp = MLPRegressor(hidden_layer_sizes=50, activation='tanh', solver='adam', alpha=0.0001,
    # learning_rate_init=0.002,
    #                    max_iter=200, shuffle=True, tol=1, verbose=False, early_stopping=False)

    mlp = MLPRegressor(hidden_layer_sizes=(50, 50), activation='relu', solver='adam', alpha=0.001,
                       max_iter=200, shuffle=True, tol=1, verbose=False, early_stopping=False)

    print(mlp.__repr__())

    mlp.fit(train, train_ref)
    predicted_2d_array = convert_to_2d_array(mlp.predict(test))
    find_solution_and_errors(predicted_2d_array, test_ref)

    print("-------------------------------")
    print(mlp.predict(test)[0])
    print(mlp.predict(test)[13])
    print(mlp.predict(test)[22])
