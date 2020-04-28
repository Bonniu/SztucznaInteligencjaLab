import openpyxl
from pathlib import Path

main_file_prefix = "dane\\pozyxAPI_only_localization_measurement"
suffix = ".xlsx"
nr_of_records = 1540
train_x = []
train_y = []
test_x = []
test_y = []


def read_file(str_nr: str):
    xlsx_file = Path(main_file_prefix + str_nr + suffix)
    wb_obj = openpyxl.load_workbook(xlsx_file)
    sheet = wb_obj.active
    for row in sheet.iter_rows(1, nr_of_records + 1):
        train_x.append(row[4].value)
        train_y.append(row[5].value)
        test_x.append(row[6].value)
        test_y.append(row[7].value)
        # for i in range(4, 8):
        #     print(row[i].value, end=" ")


if __name__ == "__main__":
    print('hello world')
    read_file("1")
    print(train_x[3])
    print(train_y[3])
    print(test_x[3])
    print(test_y[3])
