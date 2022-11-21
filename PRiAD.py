import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.datasets import make_classification
import warnings


dane0 = pd.read_csv("323609.csv")
dane0.info

# Zadanie 1 - miejsce na rozwiazanie
# liczba obiektów
count_objs = dane0.count()
print("liczba obiektow:", len(count_objs))

# liczba klas
classes_count = dane0.groupby('klasa').ngroups
print("liczba klas:", classes_count)

# liczba obiektów w każdej klasie
classes = dane0.groupby('klasa')
arr = []
for item in classes:
    arr.append(len(item))
print("liczba obiektow w kazdej klasie", arr)

# liczba atrybutów
attr_count = dane0.count()
print("liczba atrybutow:", len(attr_count))

# liczba danych brakujących
arr = []
for col in dane0:
    for item in dane0[col]:
        if(pd.isnull(item) == True):
            arr.append(1)
missing_data = len(arr)
print("liczba brakujacych danych:", missing_data)

# usunięcie danych brakujących
dane1 = dane0.iloc[:,:].dropna()

print(dane1.head())



# Zadanie 2 - miejsce na rozwiązanie  
sns.pairplot(dane1, kind = 'scatter')
sns.pairplot(dane1, hue = 'klasa', kind = 'scatter')
plt.show()

# Zadanie 3 - miejsce na rozwiązanie
plt.figure(figsize = (12, 12), dpi = 100)
sns.heatmap(dane1.corr(),annot = dane1.corr())