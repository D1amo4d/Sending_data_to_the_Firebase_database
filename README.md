То что я понял:
вообшем что бы потключить firebase к проекту сначала надо создать проект и после этого есть неслокько 
способов для потключения firebase,
1. это заходим в Tools там есть иконка firebase и название firebase,
вообшем нажимаем на нее, а после там есть разные вункции нажимаем на любой и нажимаем на синию сылку,
мы можем нажать на луюбую сылку из за того что мы еще не потключяли в данный проект,
поетому при нажатии еа любую опцию из предложенных функции, перекинет на овициальный сайт firebase,
после надо создать в внутри сайта firebase новый проект дать название и тд,
после создания проекта мы попадаем на нлавное окно редактирования проекта а именно добавление всяких приколюх,
таких как, ренистрация, база данных реального времени, вообше после этого
мы заходим в прект обратно нажимаем на Tools, там нажимаем firebase и после выходят разные ыункции которых мы можем добавить,
но так как мы практикуем добавление данных в базу, мы нажимаем на Realtime Database,
после выходят 2-синих сылок нажимаем на 1-синию сылку после на сверху вункции выходит сообшение о том что надо добавить,
sdka gradl загрузить вообшем какие то зависимости,
после мы заходим на сайт firebase в наш проект там мы выбираем   Realtime Database и там нажать плюсик дать название финуции внутри базы,
я так понял что это ячейка и даем ей определенное название, дальще переходим в нащ проект там дальше:

создаем Firebase родителя чтоле,засчет жтого мы обрашяемся к нашему проекту внутри Firebase, и можем что то делать
val database = FirebaseDatabase.getInstance()
-------------------------------------------------------------------
дальше, так как мы в нашем проекте Firebase а именно на Realtime Database мы создали ячейку чтоле и оброщяемся к ней по названии 
которую мы дали к примеру пусть будет user
val references = database.getReference("users")
--------------------------------------------------------------------------------------
дальше зайбался обеснять,но если по короче то
при нажатии на кнопку берем наш EditText с него берем текст и делаем toString() на случай если придет не текст,
потом создаем переменную в нее ложим: val references = database.getReference("users") получается обрашяемся по нзавании references.
потом делаем push()пушим наши данные и дальше делаем key хуй еге для чего.

потом проверка на то что отправилось ли или засетили ли данные в базу 
btnSend.setOnClickListener {
            val userNameText = etName.text.toString()
            val userId = references.push().key
            val userReference = references.child(userId.toString())

            userReference.setValue(userNameText).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast("Сообщение отправлено")
                } else {
                    showToast("Ошибка при отправке сообщения")
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}






полное хуета 
