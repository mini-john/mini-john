<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!-- Main -->
<!-- luxon lib -->
<script src='https://moment.github.io/luxon/global/luxon.js'></script>

<!-- fullcalendar bundle -->
<script src="<c:url value="/static/library/fullcalendar/dist/index.global.js"/>"></script>
<script src="<c:url value="/static/library/fullcalendar/packages/core/locales-all.global.js"/>"></script>

<!-- the luxon-to-fullcalendar connector. must go AFTER the luxon lib -->
<script src="<c:url value="/static/library/fullcalendar/packages/luxon2/index.global.js"/>"></script>
<!--<link rel="stylesheet" href="<c:url value="/static/library/fullcalendar/packages/list/src/index.css"/>" />-->


    <!-- Intro -->
    <section id="calendrier" class="two">
        <div class="container container-cal"  >


            <div id='calendarEl' class='calendarEl'></div>
        </div>
    </section>


</div>
<script>
    <!-- luxon.Settings.defaultOutputCalendar = 'hebrew'; -->
luxon.Settings.defaultZone='system';
var DateTime = luxon.DateTime;
var calendarEl = document.getElementById('calendarEl')
var calendar = new FullCalendar.Calendar(calendarEl,    

{
//            height: '100%',
//            expandRows: true,
            headerToolbar: {
            left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
            },
            dayCellContent: function (info) {
            var dtHebrew = DateTime.fromJSDate(info.date).reconfigure({outputCalendar: "hebrew"});
            var dtGregorian = DateTime.fromJSDate(info.date, {zone: "Europe/Paris"}).setLocale('fr');
            return dtGregorian.toFormat("dd LLL") + "/" + dtHebrew.toFormat("dd LLLL");
            },
            timeZone: 'UTC',
            events: 'https://fullcalendar.io/demo-events.json?single-day&for-resource-timeline',
            locale: 'fr',
            // titleFormat: 'DDDD d, yyyy', // you can now use Luxon format strings!
            dateClick: function (arg) {
            let dt = FullCalendar.Luxon2.toLuxonDateTime(arg.date, calendar); // calendar is required
            console.log(dt)
                    var dtHebrew = DateTime.fromJSDate(arg.date).reconfigure({outputCalendar: "hebrew"});
            dtHebrew.outputCalendar; //=> 'hebrew'
            console.log(dtHebrew.toFormat("dd LLLL"))
                    console.log('clicked on ' + dt.toISO());
            },
            eventDrop: function (arg) {
            let dur = FullCalendar.Luxon2.toLuxonDuration(arg.delta, calendar); // calendar is required
            console.log('event moved ' + dur.toISO());
}
})

calendar.render()

</script>
<script>
                    jQuery(document).ready(function () {
            jQuery("#calendrier").addClass('active');
            });
</script>