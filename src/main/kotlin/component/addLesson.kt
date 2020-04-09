package component
import kotlinext.js.asJsObject
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*

interface AddLessonProps : RProps {
    var onClick: (String) -> (Event) -> Unit
}

interface AddLessonState : RState {
    var textValue: String
}

class AddLesson : RComponent<AddLessonProps, AddLessonState>() {

    init {
        state.apply {
            textValue = ""
        }
    }

    override fun RBuilder.render() {
        h2 {
            +"Add Lesson"
        }
        input {
            attrs.value = state.textValue
            attrs.placeholder = "Lecture title"
            attrs.onChangeFunction = {
                val target = it.target?.asJsObject().unsafeCast<HTMLInputElement>()
                setState {
                    textValue = target.value
                }
            }
        }
        button {
            +"Submit"
            attrs.onClickFunction = props.onClick(state.textValue)
        }
    }
}

fun RBuilder.addLesson(
    onClick: (String) -> (Event) -> Unit
) = child(AddLesson::class) {
    attrs.onClick = onClick
}