# coding:utf-8
import sys
import gensim
import numpy as np
import jieba
import json
import joblib

from gensim.models.doc2vec import Doc2Vec, LabeledSentence
from sklearn.cluster import KMeans
import matplotlib.pyplot as plt
from sklearn.manifold import TSNE

TaggededDocument = gensim.models.doc2vec.TaggedDocument

def get_datasest():
    with open("salary/salaryInfo.json", 'r') as cf:
        docs = cf.readlines()
    content = json.loads(docs[0])
#     print(content)

    x_train = []
    #y = np.concatenate(np.ones(len(docs)))
    for idx, text in enumerate(content):
#         print(text)
        word_list = []
        filter_keys = ['degree',
                       'stockComp',
#                        'level',
#                        'company',
                       'title',
                       'yrOfExp',
                       'yrInCom',
                       'location',
                       'baseComp',
                       'totalComp' ,
                       'bonusComp']
        for k, v in text.items():
#             print(k)
            if k in filter_keys:
                word_list.append(str(v))
#         print(word_list)
        document = TaggededDocument(word_list, tags=[idx])
        x_train.append(document)
    print(x_train)

    return x_train

def train(x_train, size=200, epoch_num=1):
    model_dm = Doc2Vec(x_train,min_count=1, window = 3, vector_size = size, sample=1e-3, negative=5, workers=4)
    model_dm.train(x_train, total_examples=model_dm.corpus_count, epochs=500)
    model_dm.save('salary.model')

    return model_dm

def cluster(x_train):
    infered_vectors_list = []
    print ("load doc2vec model...")
    model_dm = Doc2Vec.load("salary.model")
    print ("load train vectors...")
    i = 0
    for text, label in x_train:
        vector = model_dm.infer_vector(text)
        infered_vectors_list.append(vector)
        i += 1

    print ("train kmean model...")
    kmean_model = KMeans(n_clusters=15)
    kmean_model.fit(infered_vectors_list)
#     labels= kmean_model.predict(infered_vectors_list) #这里是预测，可以注释掉
    cluster_centers = kmean_model.cluster_centers_
    #print("cluster_centers： " + cluster_centers)

    drawGraph(infered_vectors_list, kmean_model)
    joblib.dump(kmean_model,'salary.pkl')

#     with open("out/es.txt", 'w') as wf:
#         for i in range(len(labels)):
#             string = ""
#             text = x_train[i][0]
#             for word in text:
#                 string = string + word
#             string = string + '\t'
#             string = string + str(labels[i])
#             string = string + '\n'
#             wf.write(string)

    return cluster_centers

def drawGraph(tfidf_weight, kmeans):
    tsne = TSNE(n_components=2)
    decomposition_data = tsne.fit_transform(tfidf_weight)
    x = []
    y = []
    for i in decomposition_data:
        x.append(i[0])
        y.append(i[1])
    fig = plt.figure(figsize=(10, 10))
    ax = plt.axes()
    plt.scatter(x, y, c=kmeans.labels_, marker="x")
    plt.xticks(())
    plt.yticks(())
    plt.show()

def predict(vector):
#     load k_means_model
    kmean_model = joblib.load('salary.pkl')
    predict = ['硕士', '45000', '市场运营', '4', '4', '杭州', '240000', '340000', '55000']
    predict_1 = ['本科', '4000', '软件工程师', '4', '2', '深圳', '220000', '300000', '105000']
#     load vector
    model_dm = Doc2Vec.load("salary.model")
    print(model_dm)
#     calculator vector
    vector = model_dm.infer_vector(predict)
    vector_1 = model_dm.infer_vector(predict_1)
    print(predict)
    predict_vector = [vector_1]
    lables= kmean_model.predict(predict_vector) #这里是预测，可以注释掉

    result_map = get_train_map()

    for lable in lables:
        print(lable)
        print(result_map[lable])

#     print(result_map)

    return lables

def get_train_map():
    with open("salary/salaryInfo.json", 'r') as cf:
        docs = cf.readlines()
    content = json.loads(docs[0])
    train_map = {}
    for idx, text in enumerate(content):
        train_map[idx] = text
    return train_map

if __name__ == '__main__':
#     x_train = get_datasest()
#     model_dm = train(x_train)
#     cluster_centers = cluster(x_train)
    lables = predict(None)