from datetime import datetime
import pandas as pd
import warnings
from FileReader import FileReader
from math import sqrt
from sklearn.neural_network import MLPRegressor
from sklearn.exceptions import ConvergenceWarning

warnings.filterwarnings(action='ignore', category=ConvergenceWarning)
mlp = MLPRegressor(activation='relu', solver='adam',
                   shuffle=True, random_state=14,
                   hidden_layer_sizes=(10, 10))


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
    df.to_excel('test.xlsx', sheet_name='sheet1',
                index=False, header=True)


def print_results():
    for i in range(len(predicted)):
        print(i + 1, "predicted:", predicted[i],
              "reference:", test_ref[i],
              " error:", errors[i])


def print_layers():
    mlp.coefs_.reverse()
    for i in range(len(mlp.coefs_) - 1):
        print(i + 1, ". hidden layer")
        print(mlp.coefs_[i])
    print(len(mlp.coefs_), ". output layer")
    print(mlp.coefs_[len(mlp.coefs_) - 1])


if __name__ == "__main__":
    fp = FileReader()
    train, train_ref, test, test_ref = fp.read_files()
    errors = []
    before = datetime.now()
    mlp.fit(train, train_ref)
    predicted = list(mlp.predict(test))
    count_errors(predicted, test_ref)
    print("time elapsed:", datetime.now() - before)
    # print_results()
    print("average error:", sum(errors) / len(errors))
    print_layers()
    count_distribution()
