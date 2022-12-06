#importy
import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import matplotlib.cm as cm

from scipy.cluster.hierarchy import linkage, dendrogram, ward, fcluster
from scipy.spatial.distance import cdist
from sklearn import metrics
from sklearn.cluster import AgglomerativeClustering,KMeans
from sklearn.datasets import make_blobs
from sklearn.metrics import silhouette_samples, silhouette_score 
from mlxtend.frequent_patterns import apriori
from mlxtend.frequent_patterns import association_rules

#sciezka do pliku z danymi, prosze dostosowac
filepath = '../dane/iris.csv'






Zadanie 1



iris_raw = pd.read_csv(filepath, usecols = range(1, 6))
iris = iris_raw.drop(columns = "species")

methods = ['average', 'complete', 'single', 'ward']
percent = 25
ngroups = 3

"""
w ten sposob podgladalem wszystkie wersje dendrogramu, do sprawdzenia zostawiam tylko wyswietlanie najlepszej,
ale zostawiam rowniez ten zakomentowany fragment kodu jako czesc mojego procesu myslowego
for method in methods:
    group = linkage(iris, method = method, metric = 'euclidean')
    p = percent * max(group[:, 2]) / 100
    dendrogram(group, truncate_mode = 'lastp', color_threshold = p)
    plt.show()
"""

group = ward(iris)
condition = percent * max(group[:, 2]) / 100
dendrogram(group, truncate_mode = 'lastp', color_threshold = condition)

plt.figure(figsize = (20, 10))
for i, dist_type in enumerate(('average', 'complete', 'single', 'ward')):
    plt.subplot(2,4, i + 1)
    model = AgglomerativeClustering(linkage = dist_type, n_clusters = ngroups)
    model.fit(iris)
    plt.scatter(iris.petal_length,iris.petal_width, c = model.labels_)
    plt.title('Distance type: %s ' % dist_type, fontdict = dict(verticalalignment = 'top'))
    plt.axis('equal')
    plt.axis('off')
    plt.suptitle('Number of groups: %i' % ngroups)
    plt.subplot(2, 4, i + 5)
    errors = pd.crosstab(iris_raw['species'],model.labels_)
    sns.heatmap(errors, annot = errors)
    
    
Wnioski z analizy wyników zad. 1:

Uzyłem takiej ilości grup, jak liczba gatunków
Testowałem różne progi procentowe (10, 20, 30, ..., 90), i dla wszystkich wychodziły podobne wyniki, finalnie zostawiłem wartość mniej więcej w okolicy 50%
Najlepszym typem odległości jest complete lub ward, ponieważ wtedy grupy są najlepiej podzielone, najlepiej rozróżnialne
Najgorszym typem odległości jest single, ponieważ wtedy virginica i versicolor są łączone w pojedynczą grupę, co jest zdecydowanie zbyt dużym uogólnieniem










Zadanie 2




iris_raw = pd.read_csv(filepath, usecols = range(1, 6))
iris = iris_raw.drop(columns = 'species')

max_k = 6
range_k = range(1, max_k)
nvertical = int(max_k / 2) + 1
plt.figure(figsize = (15, 15))
plt.subplot(nvertical, 2, 1)
plt.scatter(iris['petal_length'], iris['petal_width'])
plt.title('Data set')
avg_dist = []
pos = 2
for k in range(1, max_k + 1):
    plt.subplot(nvertical, 2, pos)
    pos += 1
    kmeans = KMeans(n_clusters = k, random_state = 0)
    kmeans.fit(iris)
    cluster_labels = kmeans.fit_predict(iris)
    plt.scatter(iris.petal_length, iris.petal_width, marker='.', c = cluster_labels)
    centroids = kmeans.cluster_centers_
    plt.scatter(centroids[:, 0], centroids[:, 1], marker = 'x', c = np.array(range(k)))
    plt.title('k = %s ' % k)
    avg_dist.append(sum(np.min(cdist(iris, centroids, 'euclidean'), axis = 1)) / iris.shape[0]) 

plt.figure(figsize = (10, 5))
plt.plot(range(1, max_k + 1), avg_dist, 'bx-')
plt.xlabel('k')
plt.ylabel('Distance')
plt.title('Average distance from centroid')

group_numbers = [2, 3, 4, 5, 6]
quality = pd.Series(dtype = float)

for ngroups in group_numbers :
    fig, (ax1, ax2) = plt.subplots(1, 2)
    fig.set_size_inches(15, 5)

    ax1.set_xlim([-0.2, 1])
    ax1.set_ylim([0, len(iris) + (ngroups + 1) * 10])
    
    kmeans = KMeans(n_clusters = ngroups, random_state = 0).fit(iris)
    quality = quality.append(pd.Series(kmeans.inertia_))
    group_labels = kmeans.fit_predict(iris[['petal_length','petal_width']])

    silhouette = silhouette_samples(iris[['petal_length','petal_width']], group_labels)   
    average_silhouette = silhouette_score(iris[['petal_length','petal_width']], group_labels)
    
    line_pos = 10
    for i in range(ngroups):
        silhouette_in_group = silhouette[group_labels == i]
        silhouette_in_group.sort()

        liczebnosc_grupy = silhouette_in_group.shape[0]
        color = cm.tab10(float(i) / ngroups)
        ax1.fill_betweenx(np.arange(line_pos, line_pos + liczebnosc_grupy), 0, silhouette_in_group, color = color)

        line_pos += liczebnosc_grupy + 10
        
    ax1.set_title('Silhouette plot for certain group numbers')
    ax1.set_xlabel('Silhouette value')
    ax1.set_ylabel('Group labels')

    ax1.axvline(x = average_silhouette, color = 'black', linestyle = '--')
    ax1.set_yticks([])
    ax1.set_xticks([-0.1, 0, 0.2, 0.4, 0.6, 0.8, 1])

    colory = cm.tab10(group_labels.astype(float) / ngroups)
    ax2.scatter(iris.petal_length, iris.petal_width, marker = '.', s = 30, lw = 0, alpha = 0.7,c = colory)

    ax2.set_title('Data grouping visualisation')
    ax2.set_xlabel('Petal_length')
    ax2.set_ylabel('Petal_width')

    plt.suptitle(('Analysis of silhouette k = %d' % ngroups),  fontweight = 'bold')
    plt.figtext(0.14, 0, ('For n = %d, average silhouette value is: %.3f, Sum of distances from centroids: %.2f'
        % (ngroups, average_silhouette, kmeans.inertia_ ) ))
    
plt.plot(group_numbers, quality,'bo-')
plt.title('Elbow plot', fontsize = 14, fontweight = 'bold')
plt.xlabel('Number of groups')
plt.ylabel('Sum of distances from centroids')





Wnioski z analizy wyników zad. 2:

Wg wykresów sylwetki, najlepszą ilością grup jest 3, ponieważ wtedy algorytm w miarę dobrze odróżnia gatunki versicolor i virginica
Dla dwóch grup te dwa gatunki są łączone w jedną grupę, co nie jest dobrym wynikiem
Dla większej ilości grup, algorytm dokonuje już nadmiernych podziałów, stąd mój wniosek, że liczba grup powinna odpowiadać liczbie kategorii, które chcemy opisać/rozpoznawać
