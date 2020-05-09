import warnings
from FileReader import FileReader
from math import sqrt
from sklearn.neural_network import MLPRegressor
from sklearn.exceptions import ConvergenceWarning

warnings.filterwarnings(action='ignore', category=ConvergenceWarning)
mlp = MLPRegressor(max_iter=200, tol=1, activation='relu',
                   solver='adam', shuffle=False,
                   random_state=3, hidden_layer_sizes=(3, 3),
                   alpha=0.001, momentum=1)


def find_errors(predicted_: [], reference: []):
    errors_ = []
    for i in range(len(predicted_)):
        tmp_x = reference[i][0] - predicted_[i][0]
        tmp_y = reference[i][1] - predicted_[i][1]
        errors_.append(sqrt(tmp_x ** 2 + tmp_y ** 2))
    return errors_


def print_results():
    for i in range(len(predicted)):
        print(i + 1, "predicted:", predicted[i],
              "reference:", test_ref[i],
              " error:", errors[i])
    print("average error:", sum(errors) / len(errors))


if __name__ == "__main__":
    fp = FileReader()
    train, train_ref, test, test_ref = fp.read_files()
    mlp.fit(train, train_ref)
    predicted = list(mlp.predict(test))
    errors = find_errors(predicted, test_ref)
    print_results()
    print(mlp.coefs_[0])  # warstwa wyjsciowa
    print(mlp.coefs_[1])  # warstwa ukryta
    print(mlp.coefs_[2])  # warstwa ukryta
