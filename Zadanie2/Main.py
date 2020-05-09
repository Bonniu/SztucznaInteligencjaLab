import pandas as pd
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


def count_errors(predicted_: [], reference: []):
    for i in range(len(predicted_)):
        tmp_x = reference[i][0] - predicted_[i][0]
        tmp_y = reference[i][1] - predicted_[i][1]
        errors.append(sqrt(tmp_x ** 2 + tmp_y ** 2))


def count_distribution():
    distribution = []
    for i in range(int(max(errors) + 2)):
        distribution.append(0)
        count_elements_less_than_i(distribution, i)
    save_to_xlsx(distribution)


def count_elements_less_than_i(distribution, i):
    for j in range(len(errors)):
        if errors[j] < i:
            distribution[i] += 1 / 1540


def save_to_xlsx(tab):
    df = pd.DataFrame({"dystrybuanta": tab})
    df.to_excel('test.xlsx', sheet_name='sheet1', index=False, header=True)


def print_results():
    for i in range(len(predicted)):
        print(i + 1, "predicted:", predicted[i],
              "reference:", test_ref[i],
              " error:", errors[i])
    print("average error:", sum(errors) / len(errors))


if __name__ == "__main__":
    train, train_ref, test, test_ref = FileReader().read_files()
    mlp.fit(train, train_ref)
    predicted = list(mlp.predict(test))
    errors = []
    count_errors(predicted, test_ref)
    print_results()
    count_distribution()

    # print(mlp.coefs_[0])  # warstwa wyjsciowa
    # print(mlp.coefs_[1])  # warstwa ukryta
    # print(mlp.coefs_[2])  # warstwa ukryta
