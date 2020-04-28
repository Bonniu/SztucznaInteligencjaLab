from sklearn.neural_network import MLPRegressor

from FileReader import FileReader

nr_of_records = 1540


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
    # print(test)
    print(mlp.predict(test))  # sprawdzenie
    print(mlp.predict(test).__len__())  # sprawdzenie
    print(mlp.predict(test)[0])
    print(mlp.predict(test)[13])
    print(mlp.predict(test)[22])
