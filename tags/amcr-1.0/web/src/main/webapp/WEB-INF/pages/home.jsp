<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="home.title"/></title>
    <meta name="menu" content="Home"/>
</head>
<body class="home">

<c:redirect url="/dashboard"/>
<h2><fmt:message key="home.heading"/></h2>
<p><fmt:message key="home.message"/></p>

<ul class="glassList">
    <li>
        <a href="<c:url value='/userform'/>"><fmt:message key="menu.user"/></a>
    </li>
    <li>
        <a href="<c:url value='/fileupload'/>"><fmt:message key="menu.selectFile"/></a>
    </li>
</ul>
<%--
<div class="col-lg-12">
    <div class="panel panel-green">
        <div class="panel-heading">
            <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Survey results for survey1</h3>
        </div>
        <div class="panel-body">
            <div id="morris-area-chart" style="position: relative; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">
                <svg height="347" version="1.1" width="999" xmlns="http://www.w3.org/2000/svg"
                     style="overflow: hidden; position: relative; left: -0.333343505859375px;">
                    <desc style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Created with RaphaÃ«l 2.1.2</desc>
                    <defs style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></defs>
                    <text x="49.21875" y="313.65625" text-anchor="end" font="10px &quot;Arial&quot;" stroke="none"
                          fill="#888888"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: end; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: sans-serif;"
                          font-size="12px" font-family="sans-serif" font-weight="normal">
                        <tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">0</tspan>
                    </text>
                    <path fill="none" stroke="#aaaaaa" d="M61.71875,313.65625H974" stroke-width="0.5"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                    <text x="49.21875" y="241.4921875" text-anchor="end" font="10px &quot;Arial&quot;" stroke="none"
                          fill="#888888"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: end; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: sans-serif;"
                          font-size="12px" font-family="sans-serif" font-weight="normal">
                        <tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">7,500</tspan>
                    </text>
                    <path fill="none" stroke="#aaaaaa" d="M61.71875,241.4921875H974" stroke-width="0.5"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                    <text x="49.21875" y="169.328125" text-anchor="end" font="10px &quot;Arial&quot;" stroke="none"
                          fill="#888888"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: end; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: sans-serif;"
                          font-size="12px" font-family="sans-serif" font-weight="normal">
                        <tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">15,000</tspan>
                    </text>
                    <path fill="none" stroke="#aaaaaa" d="M61.71875,169.328125H974" stroke-width="0.5"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                    <text x="49.21875" y="97.1640625" text-anchor="end" font="10px &quot;Arial&quot;" stroke="none"
                          fill="#888888"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: end; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: sans-serif;"
                          font-size="12px" font-family="sans-serif" font-weight="normal">
                        <tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">22,500</tspan>
                    </text>
                    <path fill="none" stroke="#aaaaaa" d="M61.71875,97.1640625H974" stroke-width="0.5"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                    <text x="49.21875" y="25" text-anchor="end" font="10px &quot;Arial&quot;" stroke="none"
                          fill="#888888"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: end; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: sans-serif;"
                          font-size="12px" font-family="sans-serif" font-weight="normal">
                        <tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">30,000</tspan>
                    </text>
                    <path fill="none" stroke="#aaaaaa" d="M61.71875,25H974" stroke-width="0.5"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                    <text x="974" y="326.15625" text-anchor="middle" font="10px &quot;Arial&quot;" stroke="none"
                          fill="#888888"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: sans-serif;"
                          font-size="12px" font-family="sans-serif" font-weight="normal"
                          transform="matrix(1,0,0,1,0,6.6719)">
                        <tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">2012-06</tspan>
                    </text>
                    <text x="872.0195929526124" y="326.15625" text-anchor="middle" font="10px &quot;Arial&quot;"
                          stroke="none" fill="#888888"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: sans-serif;"
                          font-size="12px" font-family="sans-serif" font-weight="normal"
                          transform="matrix(1,0,0,1,0,6.6719)">
                        <tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">2012-03</tspan>
                    </text>
                    <text x="771.1476685905225" y="326.15625" text-anchor="middle" font="10px &quot;Arial&quot;"
                          stroke="none" fill="#888888"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: sans-serif;"
                          font-size="12px" font-family="sans-serif" font-weight="normal"
                          transform="matrix(1,0,0,1,0,6.6719)">
                        <tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">2011-12</tspan>
                    </text>
                    <text x="670.2757442284326" y="326.15625" text-anchor="middle" font="10px &quot;Arial&quot;"
                          stroke="none" fill="#888888"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: sans-serif;"
                          font-size="12px" font-family="sans-serif" font-weight="normal"
                          transform="matrix(1,0,0,1,0,6.6719)">
                        <tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">2011-09</tspan>
                    </text>
                    <text x="601.5498177399757" y="326.15625" text-anchor="middle" font="10px &quot;Arial&quot;"
                          stroke="none" fill="#888888"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: sans-serif;"
                          font-size="12px" font-family="sans-serif" font-weight="normal"
                          transform="matrix(1,0,0,1,0,6.6719)">
                        <tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">2011-07</tspan>
                    </text>
                    <text x="500.67789337788577" y="326.15625" text-anchor="middle" font="10px &quot;Arial&quot;"
                          stroke="none" fill="#888888"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: sans-serif;"
                          font-size="12px" font-family="sans-serif" font-weight="normal"
                          transform="matrix(1,0,0,1,0,6.6719)">
                        <tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">2011-04</tspan>
                    </text>
                    <text x="400.9144517010936" y="326.15625" text-anchor="middle" font="10px &quot;Arial&quot;"
                          stroke="none" fill="#888888"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: sans-serif;"
                          font-size="12px" font-family="sans-serif" font-weight="normal"
                          transform="matrix(1,0,0,1,0,6.6719)">
                        <tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">2011-01</tspan>
                    </text>
                    <text x="298.934044653706" y="326.15625" text-anchor="middle" font="10px &quot;Arial&quot;"
                          stroke="none" fill="#888888"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: sans-serif;"
                          font-size="12px" font-family="sans-serif" font-weight="normal"
                          transform="matrix(1,0,0,1,0,6.6719)">
                        <tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">2010-10</tspan>
                    </text>
                    <text x="196.95363760631835" y="326.15625" text-anchor="middle" font="10px &quot;Arial&quot;"
                          stroke="none" fill="#888888"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: sans-serif;"
                          font-size="12px" font-family="sans-serif" font-weight="normal"
                          transform="matrix(1,0,0,1,0,6.6719)">
                        <tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">2010-07</tspan>
                    </text>
                    <text x="96.08171324422844" y="326.15625" text-anchor="middle" font="10px &quot;Arial&quot;"
                          stroke="none" fill="#888888"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: sans-serif;"
                          font-size="12px" font-family="sans-serif" font-weight="normal"
                          transform="matrix(1,0,0,1,0,6.6719)">
                        <tspan dy="4" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">2010-04</tspan>
                    </text>
                    <path fill="#7cb47c" stroke="none"
                          d="M61.71875,262.535228125C87.2138517618469,257.243196875,138.20405528554068,246.261029296875,163.6991570473876,241.367103125C189.1942588092345,236.473176953125,240.18446233292826,230.08337783469943,265.6795640947752,223.38381875C290.89754518529764,216.75708095969944,341.33350736634264,190.03925080714777,366.5514884568651,188.06191562499998C391.49234887606315,186.10630940089777,441.3740697144593,206.21392645089287,466.31493013365736,207.652053125C491.81003189550427,209.12213816964288,542.8002354191981,198.71573671875,568.2953371810449,199.6947625C593.7904389428918,200.67378828124998,644.7806424665857,232.86320587431695,670.2757442284326,215.484259375C695.4937253189551,198.29421446806694,745.9296875,69.8042609375,771.1476685905225,61.418796875C796.3656496810449,53.0333328125,846.8016118620899,136.10519710126368,872.0195929526124,148.400546875C897.5146947144592,160.83101038251368,948.5048982381531,157.34167421875,974,160.32205L974,313.65625L61.71875,313.65625Z"
                          fill-opacity="1"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); fill-opacity: 1;"></path>
                    <path fill="none" stroke="#4da74d"
                          d="M61.71875,262.535228125C87.2138517618469,257.243196875,138.20405528554068,246.261029296875,163.6991570473876,241.367103125C189.1942588092345,236.473176953125,240.18446233292826,230.08337783469943,265.6795640947752,223.38381875C290.89754518529764,216.75708095969944,341.33350736634264,190.03925080714777,366.5514884568651,188.06191562499998C391.49234887606315,186.10630940089777,441.3740697144593,206.21392645089287,466.31493013365736,207.652053125C491.81003189550427,209.12213816964288,542.8002354191981,198.71573671875,568.2953371810449,199.6947625C593.7904389428918,200.67378828124998,644.7806424665857,232.86320587431695,670.2757442284326,215.484259375C695.4937253189551,198.29421446806694,745.9296875,69.8042609375,771.1476685905225,61.418796875C796.3656496810449,53.0333328125,846.8016118620899,136.10519710126368,872.0195929526124,148.400546875C897.5146947144592,160.83101038251368,948.5048982381531,157.34167421875,974,160.32205"
                          stroke-width="3" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                    <circle cx="61.71875" cy="262.535228125" r="2" fill="#4da74d" stroke="#ffffff" stroke-width="1"
                            style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="163.6991570473876" cy="241.367103125" r="2" fill="#4da74d" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="265.6795640947752" cy="223.38381875" r="2" fill="#4da74d" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="366.5514884568651" cy="188.06191562499998" r="2" fill="#4da74d" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="466.31493013365736" cy="207.652053125" r="2" fill="#4da74d" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="568.2953371810449" cy="199.6947625" r="2" fill="#4da74d" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="670.2757442284326" cy="215.484259375" r="2" fill="#4da74d" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="771.1476685905225" cy="61.418796875" r="2" fill="#4da74d" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="872.0195929526124" cy="148.400546875" r="2" fill="#4da74d" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="974" cy="160.32205" r="2" fill="#4da74d" stroke="#ffffff" stroke-width="1"
                            style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <path fill="#a7b3bc" stroke="none"
                          d="M61.71875,288.00433125C87.2138517618469,282.2167734375,138.20405528554068,269.92362539062503,163.6991570473876,264.8541C189.1942588092345,259.784574609375,240.18446233292826,250.21985906762293,265.6795640947752,247.44812812499998C290.89754518529764,244.70652469262293,341.33350736634264,245.02964857993786,366.5514884568651,242.80076250000002C391.49234887606315,240.59636967368786,441.3740697144593,232.80656851820055,466.31493013365736,229.7150125C491.81003189550427,226.55475523695054,542.8002354191981,217.662411328125,568.2953371810449,217.79350937499999C593.7904389428918,217.92460742187498,644.7806424665857,244.15928712431693,670.2757442284326,230.763796875C695.4937253189551,217.51390978056693,745.9296875,119.06826093749999,771.1476685905225,111.21199999999999C796.3656496810449,103.35573906249999,846.8016118620899,159.66617199880466,872.0195929526124,167.913709375C897.5146947144592,176.25187903005465,948.5048982381531,175.1445484375,974,177.554828125L974,313.65625L61.71875,313.65625Z"
                          fill-opacity="1"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); fill-opacity: 1;"></path>
                    <path fill="none" stroke="#7a92a3"
                          d="M61.71875,288.00433125C87.2138517618469,282.2167734375,138.20405528554068,269.92362539062503,163.6991570473876,264.8541C189.1942588092345,259.784574609375,240.18446233292826,250.21985906762293,265.6795640947752,247.44812812499998C290.89754518529764,244.70652469262293,341.33350736634264,245.02964857993786,366.5514884568651,242.80076250000002C391.49234887606315,240.59636967368786,441.3740697144593,232.80656851820055,466.31493013365736,229.7150125C491.81003189550427,226.55475523695054,542.8002354191981,217.662411328125,568.2953371810449,217.79350937499999C593.7904389428918,217.92460742187498,644.7806424665857,244.15928712431693,670.2757442284326,230.763796875C695.4937253189551,217.51390978056693,745.9296875,119.06826093749999,771.1476685905225,111.21199999999999C796.3656496810449,103.35573906249999,846.8016118620899,159.66617199880466,872.0195929526124,167.913709375C897.5146947144592,176.25187903005465,948.5048982381531,175.1445484375,974,177.554828125"
                          stroke-width="3" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                    <circle cx="61.71875" cy="288.00433125" r="2" fill="#7a92a3" stroke="#ffffff" stroke-width="1"
                            style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="163.6991570473876" cy="264.8541" r="2" fill="#7a92a3" stroke="#ffffff" stroke-width="1"
                            style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="265.6795640947752" cy="247.44812812499998" r="2" fill="#7a92a3" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="366.5514884568651" cy="242.80076250000002" r="2" fill="#7a92a3" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="466.31493013365736" cy="229.7150125" r="2" fill="#7a92a3" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="568.2953371810449" cy="217.79350937499999" r="2" fill="#7a92a3" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="670.2757442284326" cy="230.763796875" r="2" fill="#7a92a3" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="771.1476685905225" cy="111.21199999999999" r="2" fill="#7a92a3" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="872.0195929526124" cy="167.913709375" r="2" fill="#7a92a3" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="974" cy="177.554828125" r="2" fill="#7a92a3" stroke="#ffffff" stroke-width="1"
                            style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <path fill="#2577b5" stroke="none"
                          d="M61.71875,288.00433125C87.2138517618469,287.73491875,138.20405528554068,289.62802265625,163.6991570473876,286.92668125C189.1942588092345,284.22533984375,240.18446233292826,267.5896043203552,265.6795640947752,266.3936C290.89754518529764,265.21059572660516,341.33350736634264,279.70604881733425,366.5514884568651,277.410646875C391.49234887606315,275.14046912983423,441.3740697144593,250.39493307864012,466.31493013365736,248.13128125C491.81003189550427,245.8173260473901,542.8002354191981,256.70677734375,568.2953371810449,259.10021875C593.7904389428918,261.49366015625003,644.7806424665857,278.64992334357925,670.2757442284326,267.2788125C695.4937253189551,256.03130068732924,745.9296875,175.682170703125,771.1476685905225,168.625728125C796.3656496810449,161.569285546875,846.8016118620899,202.88355966103143,872.0195929526124,210.827271875C897.5146947144592,218.85827762978144,948.5048982381531,227.10026796875,974,232.5246L974,313.65625L61.71875,313.65625Z"
                          fill-opacity="1"
                          style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); fill-opacity: 1;"></path>
                    <path fill="none" stroke="#0b62a4"
                          d="M61.71875,288.00433125C87.2138517618469,287.73491875,138.20405528554068,289.62802265625,163.6991570473876,286.92668125C189.1942588092345,284.22533984375,240.18446233292826,267.5896043203552,265.6795640947752,266.3936C290.89754518529764,265.21059572660516,341.33350736634264,279.70604881733425,366.5514884568651,277.410646875C391.49234887606315,275.14046912983423,441.3740697144593,250.39493307864012,466.31493013365736,248.13128125C491.81003189550427,245.8173260473901,542.8002354191981,256.70677734375,568.2953371810449,259.10021875C593.7904389428918,261.49366015625003,644.7806424665857,278.64992334357925,670.2757442284326,267.2788125C695.4937253189551,256.03130068732924,745.9296875,175.682170703125,771.1476685905225,168.625728125C796.3656496810449,161.569285546875,846.8016118620899,202.88355966103143,872.0195929526124,210.827271875C897.5146947144592,218.85827762978144,948.5048982381531,227.10026796875,974,232.5246"
                          stroke-width="3" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                    <circle cx="61.71875" cy="288.00433125" r="2" fill="#0b62a4" stroke="#ffffff" stroke-width="1"
                            style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="163.6991570473876" cy="286.92668125" r="2" fill="#0b62a4" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="265.6795640947752" cy="266.3936" r="2" fill="#0b62a4" stroke="#ffffff" stroke-width="1"
                            style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="366.5514884568651" cy="277.410646875" r="2" fill="#0b62a4" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="466.31493013365736" cy="248.13128125" r="2" fill="#0b62a4" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="568.2953371810449" cy="259.10021875" r="2" fill="#0b62a4" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="670.2757442284326" cy="267.2788125" r="2" fill="#0b62a4" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="771.1476685905225" cy="168.625728125" r="2" fill="#0b62a4" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="872.0195929526124" cy="210.827271875" r="2" fill="#0b62a4" stroke="#ffffff"
                            stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                    <circle cx="974" cy="232.5246" r="2" fill="#0b62a4" stroke="#ffffff" stroke-width="1"
                            style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle>
                </svg>
                <div class="morris-hover morris-default-style"
                     style="display: block; left: 310.551488456865px; top: 175px;">
                    <div class="morris-hover-row-label">2010 Q4</div>
                    <div class="morris-hover-point" style="color: #0b62a4">
                Answer A:
                3,767
            </div><div class="morris-hover-point" style="color: #7A92A3">
                Answer B:
                3,597
            </div><div class="morris-hover-point" style="color: #4da74d">
                Answer C:
                5,689
            </div></div></div>
        </div>
    </div>
</div>
--%>
</body>
<script type="text/javascript">
    $(document).ready(function() {


//        window.location.href = "http://stackoverflow.com";
    });
</script>