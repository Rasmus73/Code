using IntervalDisplayLibrary.Components.Interface;
using System.Collections;
using System.Collections.Generic;
using System.Drawing;

namespace IntervalDisplayLibrary.Components
{
    internal class Composite : IComponent, IComposite, IEnumerable<IComponent>
    {
        #region Composite

        public string Name { get; set; }
        public long YCoord { get; set; }

        private List<IComponent> _children = new List<IComponent>();

        public Composite(string name)
        {
            Name = name;
        }

        public void SortComponentsByType()
        {
            _children.Sort((a, b) => { return (a.GetType().Name.CompareTo(b.GetType().Name)); });
            //_children.Sort((a, b) => {
            //    if(a is IIntervalComponent)

            //    return 1;
            //});
        }

        #endregion

        #region IComponent
        public void Add(IComponent component)
        {
            _children.Add(component);
        }

        //public void Insert(IComponent component, int index = -1)
        //{
        //    if(index == -1)
        //    {
        //        _children.Add(component);
        //    }
        //    else
        //    {
        //        _children.Insert(index, component);
        //    }
        //}

        public void Draw(Graphics graphics)
        {
            Pen myPen;
            myPen = new Pen(Color.Black);

            Font font = new Font("TimesNewRoman", 8);
            graphics.DrawString(Name, font, new SolidBrush(Color.Red), 1, YCoord);

            foreach (IComponent component in _children)
            {
                //if(component is IComposite)
                //{
                //    ((IComposite)component).CalculatePosition(0,0);
                //}

                component.Draw(graphics);
            }
        }

        #endregion


        #region IComposite

        public void Clear()
        {
            _children.Clear(); // TODO: loop nested composites.... ?!
        }

        public void CalculatePosition(int width, int resolutionInPixels)
        {
            var dd = _children[0];
        }
        #endregion

        #region IEnumerable
        public IEnumerator<IComponent> GetEnumerator()
        {
            foreach (IComponent child in _children)
            {
                yield return child;
            }
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }

        #endregion

    }
}